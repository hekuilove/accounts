package org.quinn.accounts.util.paginate;

import java.util.HashMap;
import java.util.Map;

public class TestUser {

	private String name;

	private Integer age;

	private String address;

	private String department;

	public static void main(String[] args) {
		int a = 1;
		int b = 10000;
		while (a < b)
			a <<= 1;
		System.out.println(a);
		HashMap<String, Object> map = new HashMap<String, Object>(100);
		for (int i = 0; i < 98; i++) {
			if (i == 95) {
				System.out.println(2);
			}
			map.put("a" + i, 1);

		}
	}

	static void p() {
		String s = "aa";
		int a = 1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
}
