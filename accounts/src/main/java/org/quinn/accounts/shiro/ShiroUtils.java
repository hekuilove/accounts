package org.quinn.accounts.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

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
}
