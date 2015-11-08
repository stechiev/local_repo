package ru.home.testapp.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public DateUtils() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static Date getCurrentDateWithoutTime(){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}
	
	public static Date getCurrentDateLimit(){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.HOUR_OF_DAY, 11);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

}
