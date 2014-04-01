package org.quinn.accounts.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.quinn.accounts.shiro.realm.AuthRealm;

public class ShiroUtils {

	public static synchronized Subject getSubject() {
		Subject subject;
		try {
			subject = SecurityUtils.getSubject();
		} catch (UnavailableSecurityManagerException e) {
			subject = null;
		}
		if (subject == null) {
			Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:accounts-login-realm.ini");
			SecurityManager sm = factory.getInstance();
			SecurityUtils.setSecurityManager(sm);
			subject = SecurityUtils.getSubject();
		}
		return subject;
	}

	public static synchronized SecurityManager getSecurityManager() {
		return SecurityUtils.getSecurityManager();
	}

	public static void clearCache() {
		SecurityManager sm = SecurityUtils.getSecurityManager();
		RealmSecurityManager rsm = (RealmSecurityManager) sm;
		((AuthRealm) rsm.getRealms().iterator().next()).clearCache(ShiroUtils.getSubject().getPrincipals());
	}

	public static void clearCachedAuthenticationInfo() {
		SecurityManager sm = SecurityUtils.getSecurityManager();
		RealmSecurityManager rsm = (RealmSecurityManager) sm;
		((AuthRealm) rsm.getRealms().iterator().next()).clearCachedAuthenticationInfo(ShiroUtils.getSubject().getPrincipals());
	}

	public static void clearCachedAuthorizationInfo() {
		SecurityManager sm = SecurityUtils.getSecurityManager();
		RealmSecurityManager rsm = (RealmSecurityManager) sm;
		((AuthRealm) rsm.getRealms().iterator().next()).clearCachedAuthorizationInfo(ShiroUtils.getSubject().getPrincipals());
	}

	public static void clearAllCurrentCache() {
		ShiroUtils.clearCache();
		ShiroUtils.clearCachedAuthenticationInfo();
		ShiroUtils.clearCachedAuthorizationInfo();
	}

	public static void clearAllCache() {
		SecurityManager sm = SecurityUtils.getSecurityManager();
		RealmSecurityManager rsm = (RealmSecurityManager) sm;
		((AuthRealm) rsm.getRealms().iterator().next()).clearAllCache();
	}
}
