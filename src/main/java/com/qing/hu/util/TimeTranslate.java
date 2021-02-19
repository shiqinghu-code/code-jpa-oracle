package com.qing.hu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTranslate {
	private static SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat sdfTime = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat sdfTimeNoSeconds = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");

	private static SimpleDateFormat nyrMsc = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");

	private static SimpleDateFormat sdfMsec = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss:SSS");

	private static SimpleDateFormat sdfMsecs = new SimpleDateFormat(
			"HH:mm");

	public static long translateTime(String date) throws ParseException {
		if (date == null || "".equals(date) || "0".equals(date)) {
			return 0;
		}
		long timeValue = sdfDate.parse(date).getTime();
		return timeValue;
	}

	public static String translateLong(long time) {
		if (time == 0) {
			return "";
		}
		Date date = new Date(time);
		return sdfDate.format(date);
	}

	public static long translateLongTime(String date) throws ParseException {
		if (date == null || "".equals(date) || "0".equals(date)) {
			return 0;
		}
		long timeValue = sdfTime.parse(date).getTime();
		return timeValue;
	}

	public static long translateLongTimeNoSeconds(String date)
			throws ParseException {
		if (date == null || "".equals(date) || "0".equals(date)) {
			return 0;
		}
		long timeValue = sdfTimeNoSeconds.parse(date).getTime();
		return timeValue;
	}

	public static String translateTimeLong(long time) {
		if (time == 0) {
			return "";
		}
		Date date = new Date(time);
		return sdfTime.format(date);
	}

	public static String translateTimeLongNoSeconds(long time) {
		if (time == 0) {
			return "";
		}
		Date date = new Date(time);
		return sdfTimeNoSeconds.format(date);
	}

	public static String translateTimeLongMsec(long time) {
		if (time == 0) {
			return "";
		}
		Date date = new Date(time);
		return sdfMsec.format(date);
	}
	public static String translateTimeLongMsecs(long time) {
		if (time == 0) {
			return "";
		}
		Date date = new Date(time);
		return sdfMsecs.format(date);
	}

	public static String translateTimeLongNoSeconds1(long time) {
		if (time == 0) {
			return "";

		}
		Date date = new Date(time);
		return nyrMsc.format(date);
	}

}
