package com.wangcc.date.test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

public class DateUtilTest {
	@Test
	public void test() throws InterruptedException, ParseException {
		// date1 created
		Date date1 = new Date();
		// Print the date and time at this instant
		System.out.println("The time right now is >>" + date1);

		Thread.currentThread();
		// Thread sleep for 1000 ms
		Thread.sleep(DateUtils.MILLIS_PER_SECOND);

		// date2 created.
		Date date2 = new Date();
		System.out.println("The time right now is >>" + date2);

		// Check if date1 and date2 have the same day
		System.out.println("Is Same Day >> " + DateUtils.isSameDay(date1, date2));

		// Check if date1 and date2 have the same instance
		System.out.println("Is Same Instant >> " + DateUtils.isSameInstant(date1, date2));

		// Round the hour
		System.out.println("Date after rounding >>" + DateUtils.round(date1, Calendar.HOUR));

		// Truncate the hour
		System.out.println("Date after truncation >>" + DateUtils.truncate(date1, Calendar.HOUR));

		// Three dates in three different formats
		String[] dates = { "2005.03.24 11:03:26", "2005-03-24 11:03", "2005/03/24" };

		// Iterate over dates and parse strings to java.util.Date objects
		for (int i = 0; i < dates.length; i++) {
			Date parsedDate = DateUtils.parseDate(dates[i], new String[] { "yyyy/MM/dd", "yyyy.MM.dd HH:mm:ss",
					"yyyy-MM-dd HH:mm" });

			System.out.println("Parsed Date is >>" + parsedDate);
		}

		// Display date in HH:mm:ss format
		System.out.println("Now >>" + DateFormatUtils.ISO_TIME_NO_T_FORMAT.format(System.currentTimeMillis()));
	}
}
