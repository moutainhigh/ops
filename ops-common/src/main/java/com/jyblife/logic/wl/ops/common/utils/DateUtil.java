package com.jyblife.logic.wl.ops.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @描述 日期转换、计算工具类
 */
public class DateUtil {

	public static final String START_TIME = " 00:00:00";
	public static final String END_TIME = " 23:59:59";
	public static final String FOREVER_TIME = "9999-12-31 23:59:59";
	
	/**
	 * yyyy-MM-dd 时间格式
	 */
	public static final DateFormat DATE_FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * yyyy-MM-dd HH:mm:ss 时间格式
	 */
	public static final DateFormat DATE_FORMAT_DATETIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * yyyy-MM-dd HH:mm 时间格式
	 */
	public static final DateFormat DATE_FORMAT_DATETIME_NOSECOND = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	/**
	 * yyyy-MM-dd HH 时间格式
	 */
	public static final DateFormat DATE_FORMAT_DATETIME_NOMINUTE = new SimpleDateFormat("yyyy-MM-dd HH");
	/**
	 * HH:mm:ss 时间格式
	 */
	public static final DateFormat DATE_FORMAT_TIME = new SimpleDateFormat("HH:mm:ss");
	/**
	 * HH:mm 时间格式
	 */
	public static final DateFormat DATE_FORMAT_TIME_NOSECOND = new SimpleDateFormat("HH:mm");
	/**
	 * yyyy-MM-dd HH:mm:ss.SSS 时间格式
	 */
	public static final DateFormat DATE_FORMAT_TIMESTAMP = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	/**
	 * 取当前系统日期
	 * 
	 * @return 当前系统日期（Long格式）
	 */
	public static long getCurrentTimeInMillis() {
		return Calendar.getInstance().getTimeInMillis();
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @param style
	 * @return
	 */
	public static String format(Date date, String style) {
		DateFormat dateFormat = new SimpleDateFormat(style);
		return dateFormat.format(date);
	}

	public static Date parse(String strDate, String style) {
		SimpleDateFormat df = new SimpleDateFormat(style);
		try {
			return df.parse(strDate);
		} catch (ParseException e) {
			return new Date();
		}
	}

	/**
	 * 根据日期字符串是否含有时间决定转换为日期还是日期时间还是时间
	 * 
	 * @param dateString
	 *            时间字符串
	 * @return 格式化的时间
	 * @throws ParseException
	 */
	public static Date parse(String dateString) throws ParseException {
		if (dateString.trim().indexOf(" ") > 0 && dateString.trim().indexOf(".") > 0) {
			return new java.sql.Timestamp(DATE_FORMAT_TIMESTAMP.parse(dateString).getTime());
		} else if (dateString.trim().indexOf(" ") > 0) {
			if (dateString.trim().indexOf(":") > 0) {
				// 如果有两个:，则有时分秒,一个冒号只有时分
				if (dateString.trim().indexOf(":") != dateString.trim().lastIndexOf(":")) {
					return new java.sql.Timestamp(DATE_FORMAT_DATETIME.parse(dateString).getTime());
				} else {
					return new java.sql.Timestamp(DATE_FORMAT_DATETIME_NOSECOND.parse(dateString).getTime());
				}
			} else {
				return new java.sql.Timestamp(DATE_FORMAT_DATETIME_NOMINUTE.parse(dateString).getTime());
			}
		} else if (dateString.indexOf(":") > 0) {
			// 如果有两个:，则有时分秒,一个冒号只有时分
			if (dateString.trim().indexOf(":") != dateString.trim().lastIndexOf(":")) {
				return new java.sql.Time(DATE_FORMAT_TIME.parse(dateString).getTime());
			} else {
				return new java.sql.Time(DATE_FORMAT_TIME_NOSECOND.parse(dateString).getTime());
			}
		}

		return new java.sql.Date(DATE_FORMAT_DATE.parse(dateString).getTime());
	}
	
	/**
	 * 获取开始和结束时间的毫秒差
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return
	 */
	public static long getTime(Date startTime, Date endTime) {
		return endTime.getTime() - startTime.getTime();
	}

	/**
	 * 获取指定时间到系统时间的持续时间
	 * 
	 * @param date
	 *            指定时间
	 * @return
	 */
	public static String getDurationTime(Date date) {
		return getDurationTime(date, new Date());
	}

	/**
	 * 获取持续时间
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static String getDurationTime(Date startTime, Date endTime) {
		if (startTime == null || endTime == null)
			return "";
		Long millseconds = getTime(startTime, endTime);
		return getTime(millseconds);
	}

	/**
	 * 根据长整形的毫秒数返回字符串类型的时间段
	 * 
	 * @param millseconds
	 *            毫秒数
	 * @return
	 */
	public static String getTime(Long millseconds) {
		StringBuffer time = new StringBuffer();
		if (millseconds == null)
			return "";
		int days = (int) (long) millseconds / 1000 / 60 / 60 / 24;
		if (days > 0)
			time.append(days).append("天");
		long hourMillseconds = millseconds - days * 1000 * 60 * 60 * 24;
		int hours = (int) hourMillseconds / 1000 / 60 / 60;
		if (hours > 0)
			time.append(hours).append("小时");
		long minuteMillseconds = millseconds - days * 1000 * 60 * 60 * 24
				- hours * 1000 * 60 * 60;
		int minutes = (int) minuteMillseconds / 1000 / 60;
		if (minutes > 0)
			time.append(minutes).append("分钟");
		return time.toString();
	}
	
	/**
	 * 获取当天开始时间
	 * 
	 * @return
	 */
	public static java.util.Date getDayBegin() {
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	/**
	 * 获取当天的结束时间
	 * 
	 * @return
	 */
	public static java.util.Date getDayEnd() {
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}

	/**
	 * 获取明天的开始时间
	 * 
	 * @return
	 */
	public static Date getBeginDayOfTomorrow() {
		Calendar cal = new GregorianCalendar();
		cal.setTime(getDayBegin());
		cal.add(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}
	
	/**
	 * 获取获取永久时间
	 * 
	 * @return
	 */
	public static Date getForeverTime() {
		return parse(FOREVER_TIME, "yyyy-MM-dd HH:mm:ss");
	}
	
	public static void main(String[] args) {
		Date cur_date = new Date();
		Date date = DateUtil.parse(DateUtil.format(cur_date, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd");
		Date date1 = DateUtil.parse("2018-10-30", "yyyy-MM-dd");
		Date date2 = DateUtil.parse("2018-10-28", "yyyy-MM-dd");
		System.out.println(date.before(date1));
		System.out.println(date.after(date2));
	}

}
