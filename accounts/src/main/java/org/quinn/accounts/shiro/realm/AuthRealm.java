package org.quinn.accounts.shiro.realm;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.quinn.accounts.context.MyApplicationContext;
import org.quinn.accounts.model.Permission;
import org.quinn.accounts.model.Role;
import org.quinn.accounts.model.User;
import org.quinn.accounts.service.base.IPermissionService;
import org.quinn.accounts.service.base.IRoleService;
import org.quinn.accounts.service.base.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthRealm extends AuthorizingRealm {

	@Autowired
	private IRoleService roleService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		IRoleService roleService = MyApplicationContext.getBeansByType(IRoleService.class);
		IPermissionService permissionService = MyApplicationContext.getBeansByType(IPermissionService.class);
		List<Role> roles = roleService.findByUsername((String) principals.getPrimaryPrincipal());
		List<String> roleIds = this.getRoleIds(roles);
		Set<Permission> permissions = permissionService.findByRoles(roleIds);
		SimpleAuthorizationInfo author = new SimpleAuthorizationInfo();
		for (Role role : roles)
			author.addRole(role.getRoleName());
		if (permissions != null)
			for (Permission per : permissions)
				if (per.getPermissionType() == 2)
					author.addObjectPermission(new WildcardPermission(per.getPermissionName()));
				else if (per.getPermissionType() == 1)
					author.addStringPermission(per.getPermissionName());
		return author;
	}

	private List<String> getRoleIds(List<Role> roles) {
		if (roles != null) {
			List<String> ids = new ArrayList<String>(roles.size());
			for (Role role : roles)
				ids.add(role.getRoleId());
			return ids;
		}
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken tok = (UsernamePasswordToken) token;
		String username = tok.getUsername();
		String password = new String(tok.getPassword());
		IUserService userService = MyApplicationContext.getBeansByType(IUserService.class);
		if (userService.findByUsername(username) == null)
			throw new UnknownAccountException();
		User user = userService.findByUsernameAndPassword(username, password);
		if (user == null)
			throw new IncorrectCredentialsException();
		return new SimpleAuthenticationInfo(username, password, getName());
	}

	@Override
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}

	@Override
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}

	public void clearAllCache() {
		super.getAuthenticationCache().clear();
		super.getAuthorizationCache().clear();
	}
}
