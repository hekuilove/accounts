package org.quinn.accounts.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParam {

	private String time1;
	private String time2;

	public String getTime1() {
		return time1;
	}

	public void setTime1(String time1) {
		this.time1 = time1;
	}

	public String getTime2() {
		return time2;
	}

	public void setTime2(String time2) {
		this.time2 = time2;
	}

	public static Date parseStr2Date(String str, SimpleDateFormat sdf, Date defDate) {
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			return defDate;
		}
	}

}
