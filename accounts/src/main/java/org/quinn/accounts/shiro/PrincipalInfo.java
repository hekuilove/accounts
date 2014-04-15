package org.quinn.accounts.shiro;

import java.security.Principal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.ShiroException;

/**
 * 用户验证成功后的身份信息
 * @author 奎哥
 *
 */
public class PrincipalInfo implements Principal {

	@SuppressWarnings("unused")
	private PrincipalInfo() {
		//实例化对象时必须要拥有一个username
	}

	public PrincipalInfo(String username) {
		if (username == null)
			throw new ShiroException("username cannot null!!");
		this.username = username;
	}

	public PrincipalInfo(String username, Collection<String> roles, Collection<String> permissions) {
		if (username == null)
			throw new ShiroException("username cannot null!!");
		this.username = username;
		this.roles.addAll(roles);
		this.permissions.addAll(permissions);
	}

	@Override
	public String getName() {
		return "PrincipalInfo";
	}

	private String username;
	private Set<String> roles = new HashSet<String>();
	private Set<String> permissions = new HashSet<String>();

	@Override
	public int hashCode() {
		return this.username.hashCode();
	}

	@Override
	public String toString() {
		return this.username.toString();
	}

	@Override
	public boolean equals(Object obj) {
		return this.username.equals(obj);
	}

	public String getUsername() {
		return username;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public Set<String> getPermissions() {
		return permissions;
	}

	public void addRole(String role) {
		this.roles.add(role);
	}

	public void addRole(Collection<String> roles) {
		this.roles.addAll(roles);
	}

	public void addRole(Set<String> roles) {
		this.roles.addAll(roles);
	}

	public void addPermission(String permission) {
		this.permissions.add(permission);
	}

	public void addAllPermission(Set<String> permissions) {
		this.permissions.addAll(permissions);
	}

	/**
	 * 清空权限
	 */
	public void clearPermissions() {
		this.permissions.clear();
	}

	/**
	 * 清空角色
	 */
	public void clearRoles() {
		this.roles.clear();
	}

}
