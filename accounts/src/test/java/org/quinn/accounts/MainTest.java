package org.quinn.accounts;

public class MainTest {

	static {
		Thread t = new Thread() {
			public void run() {
				System.out.println(sb);
				System.out.println("come in");
				sb = "250";
			};
		};
		t.start();
	}
	static String sb = "sb";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(sb);
	}

}
