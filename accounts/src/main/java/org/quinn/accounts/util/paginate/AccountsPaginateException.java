package org.quinn.accounts.util.paginate;

public class AccountsPaginateException extends Exception {


	/**
	 */
	private static final long serialVersionUID = 1L;

	public AccountsPaginateException(String msg) {
		super(msg);
	}

	public AccountsPaginateException() {
		super();
	}

	public AccountsPaginateException(Throwable e) {
		super(e);
	}
}
