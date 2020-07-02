package com.hms.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil 
{
	public static Date convertStringToDate(String dateInString, String format) 
	{
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date date = null;
		
		try 
		{
			System.out.println("Date String:"+dateInString);
			date = formatter.parse(dateInString);
		}
		catch(ParseException e)
		{
			e.printStackTrace();
		}
		return date;
	}
}
