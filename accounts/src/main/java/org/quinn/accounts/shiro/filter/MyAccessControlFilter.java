//package org.quinn.accounts.shiro.filter;
//
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//
//import org.apache.shiro.subject.Subject;
//import org.apache.shiro.web.filter.AccessControlFilter;
//
//public class MyAccessControlFilter extends AccessControlFilter {
//
//	@Override
//	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
//		Subject subject = super.getSubject(request, response);
//		return subject.isAuthenticated();
//	}
//
//	@Override
//	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//		return false;
//	}
//
//}
