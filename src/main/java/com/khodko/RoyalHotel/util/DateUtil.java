package com.khodko.RoyalHotel.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;


public class DateUtil {

	public static final String DATE_FORMATTER = "yyyy-MM-dd";
	
	public static String dateToStr(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMATTER);
		return sdf.format(date);			
	}
	
	public static Date strToDate(String strDate) throws ParseException {
		return new SimpleDateFormat(DATE_FORMATTER).parse(strDate);
	}

	public static Date plusDays(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}
	
	public static Optional<Date> strToOptDate(String strDate) {
		Date date = null;
		try {			
	        date = strToDate(strDate);           
	    } catch (ParseException e) {
	    }		
		return Optional.ofNullable(date);
	}

}
