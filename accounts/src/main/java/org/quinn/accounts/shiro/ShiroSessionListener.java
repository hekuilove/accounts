package org.quinn.accounts.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

/**
 * 监听session的各种事件
 * @author WS-SH-L1051
 *
 */
public class ShiroSessionListener implements SessionListener {

	/**
	 * SESSION开始事件
	 */
	public void onStart(Session session) {
		System.out.println("嘿嘿，开始了");
	}

	/**
	 * SESSION停止事件
	 */
	public void onStop(Session session) {
		System.out.println("停止了，卧槽");
	}

	/**
	 * SESSION过期事件
	 */
	public void onExpiration(Session session) {
		System.out.println("尼玛，过期了");
	}

}
