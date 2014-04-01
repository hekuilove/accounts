package org.quinn.accounts.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;

/**
 * 如果仅仅需要监听一个事件 ，使用该类
 * @author WS-SH-L1051
 *
 */
public class ShiroSessionAdapter extends SessionListenerAdapter {

	@Override
	public void onExpiration(Session session) {
		super.onExpiration(session);
	}

	@Override
	public void onStart(Session session) {
		super.onStart(session);
	}

	@Override
	public void onStop(Session session) {
		super.onStop(session);
	}
}
