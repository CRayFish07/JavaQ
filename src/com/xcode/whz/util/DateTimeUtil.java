package com.xcode.whz.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 
 * @author JUDY
 * @version 1.0
 * @since 日期格式转换类
 *
 */
public class DateTimeUtil {
	
    /**
     * 根据传入的字符串返回Date，格式为"yyyy-MM-dd"
     * @param src
     * @return Date
     */
	public static Date parseDate(String src) {
		return parse(src, "yyyy-MM-dd");
	}
	  
	/**
     * 根据传入的字符串返回Date，格式为"yyyy-MM-dd HH:mm:ss"
     * @param src
     * @return Date
     */
	public static Date parseDatetime(String src) {
		return parse(src, "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
     * 根据传入的字符串返回Date，格式为"yyyy-MM-dd HH:mm:ss SSS"
     * @param src
     * @return Date
     */
	public static Date parseDatetime1(String src) {
		return parse(src, "yyyy-MM-dd HH:mm:ss SSS");
	}

	/**
	 * 根据传入的日期字符串和格式返回Date
	 * @param src
	 * @param pattern
	 * @return Date
	 */
	public static Date parse(String src, String pattern) {
		if (pattern == null)
			throw new IllegalArgumentException("日期格式不能为空");
		if (src == null)
			return null;
		try {
			return new SimpleDateFormat(pattern).parse(src);
		} catch (ParseException ex) {
			throw new IllegalArgumentException("日期格式转换出错,src=" + src
					+ ",pattern=" + pattern);
		}
	}
	
	/**
	 * 格式化日期为yyyy-MM-dd
	 * @param src
	 * @return String
	 */
	public static String formatDate(Date src) {
		return format(src, "yyyy-MM-dd");
	}
	/**
	 * 格式化日期为MM-dd
	 * @param src
	 * @return String
	 */
	public static String formatDate3(Date src) {
		return format(src, "MM-dd");
	}
	/**
	 * 格式化日期为EEEE
	 * @param src
	 * @return String
	 */
	public static String formatDate4(Date src) {
		return format(src, "EEEE");
	}
	/**
	 * 格式化日期为yyyy-MM-dd HH:mm
	 * @param src
	 * @return yyyy-MM-dd HH:mm
	 */
	public static String formatDate2(Date src) {
		return format(src, "yyyy-MM-dd HH:mm");
	}

	/**
	 * 格式化日期为HHmmss
	 * @param src
	 * @return String
	 */
	public static String formatNot_Time(Date src) {
		return format(src, "HHmmss");
	}

	/**
	 * 格式化日期为HH:mm:ss
	 * @param src
	 * @return String
	 */
	public static String formatTime(Date src) {
		return format(src, "HH:mm:ss");
	}

	/**
	 * 格式化日期为HH:00:00
	 * @param src
	 * @return String
	 */
	public static String formatTime1(Date src) {
		return format(src, "HH:00:00");
	}

	/**
	 * 格式化日期为yyyyMMdd
	 * @param src
	 * @return String
	 */
	public static String formatNot_Date(Date src) {
		return format(src, "yyyyMMdd");
	}
	/**
	 * 格式化日期为yyyyMM
	 * @param src
	 * @return String
	 */
	public static String formatNot_M(Date src) {
		return format(src, "yyyyMM");
	}

	/**
	 * 格式化日期为yyyy-MM-dd HH:mm:ss
	 * @param src
	 * @return String
	 */
	public static String formatDatetime(Date src) {
		return format(src, "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 获取当天日期字符串
	 * @param src
	 * @return String
	 */
	public static String nowdate(String src) {
		return today(src,"yyyy-MM-dd");
	}
    
	/**
	 * 获取当天日期+时间字符串
	 * @param src
	 * @return String
	 */
	public static String nowdatetime(String src) {
		return today(src,"yyyy-MM-dd HH:mm:ss SSS");
	}
    /**
     * 根据传入的日期字符串和格式化pattern返回指定格式化的日期字符串
     * @param src
     * @param pattern
     * @return String
     */
	public static String today(String src,String pattern) {
		if (pattern == null)
			throw new IllegalArgumentException("日期格式不能为空");
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String dt = sdf.format(parseDatetime1(src));
		return dt;
	}
   
	/**
	 * 格式化日期为日历时间
	 * @param src
	 * @return Date
	 */
	public static Date formatSysDate(Date src) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(src);

		Calendar currCalendar = Calendar.getInstance();
		currCalendar.setTime(new Date());
		currCalendar.set(calendar.get(Calendar.YEAR), calendar
				.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

		return currCalendar.getTime();
	}
	
	/**
	 * 按指定格式格式化传入日期并返回
	 * @param src
	 * @param pattern
	 * @return String
	 */
	public static String format(Date src, String pattern) {
		if (pattern == null)
			throw new IllegalArgumentException("日期格式不能为空");
		if (src == null)
			return null;
		return new SimpleDateFormat(pattern).format(src);
	}
	
	/**
	 * 根据日历判断是否为同一天
	 * @param cal1
	 * @param cal2
	 * @return boolean
	 */
	public static boolean isSameDay(Calendar cal1, Calendar cal2) {
		if (cal1 == null || cal2 == null)
			return false;
		return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA)
				&& cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1
				.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
	}
	
    /**
     * 根据Date判断是否为同一天
     * @param date1
     * @param date2
     * @return boolean
     */
	public static boolean isSameDay(Date date1, Date date2) {
		if (date1 == null || date2 == null)
			return false;
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return isSameDay(cal1, cal2);
	}
	
    /**
     * 根据Date判断时分是否相等
     * @param date1
     * @param date2
     * @return boolean
     */
	public static boolean isSameInstant(Date date1, Date date2) {
		if (date1 == null || date2 == null)
			return false;
		return date1.getTime() == date2.getTime();
	}
	
    /**
     * 根据日历判断时分是否相等
     * @param cal1
     * @param cal2
     * @return boolean
     */
	public static boolean isSameInstant(Calendar cal1, Calendar cal2) {
		if (cal1 == null || cal2 == null)
			return false;
		return cal1.getTime().getTime() == cal2.getTime().getTime();
	}
	
    /**
     * 增加年
     * @param date
     * @param amount
     * @return Date
     */
	public static Date addYears(Date date, int amount) {
		return add(date, Calendar.YEAR, amount);
	}
    
	/**
	 * 增加月
	 * @param date
	 * @param amount
	 * @return Date
	 */
	public static Date addMonths(Date date, int amount) {
		return add(date, Calendar.MONTH, amount);
	}
    
	/**
	 * 增加周
	 * @param date
	 * @param amount
	 * @return Date
	 */
	public static Date addWeeks(Date date, int amount) {
		return add(date, Calendar.WEEK_OF_YEAR, amount);
	}
    
	/**
	 * 增加天
	 * @param date
	 * @param amount
	 * @return Date
	 */
	public static Date addDays(Date date, int amount) {
		return add(date, Calendar.DAY_OF_MONTH, amount);
	}
    
	/**
	 * 增加小时
	 * @param date
	 * @param amount
	 * @return Date
	 */
	public static Date addHours(Date date, int amount) {
		return add(date, Calendar.HOUR_OF_DAY, amount);
	}
    
	/**
	 * 增加分钟
	 * @param date
	 * @param amount
	 * @return Date
	 */
	public static Date addMinutes(Date date, int amount) {
		return add(date, Calendar.MINUTE, amount);
	}
    
	/**
	 * 增加秒
	 * @param date
	 * @param amount
	 * @return Date
	 */
	public static Date addSeconds(Date date, int amount) {
		return add(date, Calendar.SECOND, amount);
	}

	/**
	 * 增加毫秒
	 * @param date
	 * @param amount
	 * @return Date
	 */
	public static Date addMilliseconds(Date date, int amount) {
		return add(date, Calendar.MILLISECOND, amount);
	}
    
	/**
	 * 根据日历选项calendarField增加
	 * @param date
	 * @param calendarField
	 * @param amount
	 * @return Date
	 */
	public static Date add(Date date, int calendarField, int amount) {
		if (date == null)
			throw new IllegalArgumentException("日期不能为空");

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(calendarField, amount);
		return c.getTime();
	}

	public static int getLastDayOfMonth(int y, int m) {
		boolean IsLeapYear = (y % 4 == 0) && (y % 100 != 0) || (y % 400 == 0);
		int days = 0;
		switch (m) {
		case 2:
			if (IsLeapYear) {
				days = 29;
			} else
				days = 28;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			days = 30;
			break;
		default:
			days = 31;
			break;
		}
		return days;
	}

	/**
	 * 中文星期几
	 * @return
	 */
	public static String getCnWeekDay() {
		return getCnWeekDay(new java.util.Date());
	}

	/**
	 * 中文星期几
	 * @return
	 */
	public static String getCnWeekDay(Date date) {
		String[] weekDays = { "日", "一", "二", "三", "四", "五", "六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return "星期" + weekDays[w];
	}

	/**
	 * 
	 * 功能：计算两个任意时间中间的间隔天数
	 * 参数：Date startday,Date endday  ===> 传进Date对象
	 * 作者：Judy
	 * 时间：2011-5-7下午09:08:13
	 */
	public static int getIntervalDays(Date startday, Date endday) {
		if (startday.after(endday)) {
			Date cal = startday;
			startday = endday;
			endday = cal;
		}
		long sl = startday.getTime();
		long el = endday.getTime();
		long ei = el - sl;
		return (int) (ei / (1000 * 60 * 60 * 24));
	}

	/**
	 * 
	 * 功能：计算两个任意时间中间的间隔天数
	 * 参数：传进Calendar对象
	 * 作者：Judy
	 * 时间：2011-5-7下午09:09:09
	 */
	public static int getIntervalDays(Calendar startday, Calendar endday) {
		if (startday.after(endday)) {
			Calendar cal = startday;
			startday = endday;
			endday = cal;
		}
		long sl = startday.getTimeInMillis();
		long el = endday.getTimeInMillis();
		long ei = el - sl;
		return (int) (ei / (1000 * 60 * 60 * 24));
	}

	/**
	 * 
	 * 功能：取得当前的月份
	 * 参数：传进Date对象
	 * 作者：Judy
	 * 时间：2011-5-7下午09:09:09
	 */
	public static int getMonth(){
		Calendar c=Calendar.getInstance();
		c.setTime(new Date());
		return c.get(c.MONTH)+1;
	}

	/**
	 * 
	 * 功能：根据月份取得时间
	 * 参数：传进月份int值
	 * 时间：2011-5-7下午09:09:09
	 */
	public static String monthToDate(int month){
		Calendar c=Calendar.getInstance();
		//月份大于当前月则认为是去年的那个月
		if(month>getMonth()){
			c.add(c.YEAR, -1);
		}
		c.set(c.MONTH, month-1);
		return format(c.getTime(),"yyyyMM");
	}
	
	/**
	 * 
	 * 功能：根据月份取得时间
	 * 参数：传进月份int值
	 * 时间：2011-5-7下午09:09:09
	 */
	public static String monthToDate1(int month){
		Calendar c=Calendar.getInstance();
		//月份大于当前月则认为是去年的那个月
		if(month>getMonth()){
			c.add(c.YEAR, -1);
		}
		c.set(c.MONTH, month-1);
		return format(c.getTime(),"yyyy-MM");
	}
	
	/**
	 * 
	 * 功能：获取当前年份YYYY格式
	 * 作者：Judy
	 * 时间：2011-5-7下午09:09:09
	 */
	public static String getNowYear(){
		Calendar c=Calendar.getInstance();
		return format(c.getTime(),"yy");
	}
	
	/**
	 * 
	 * 功能：获取当前日格式yyyyMMdd
	 * 作者：Judy
	 * 时间：2011-5-7下午09:09:09
	 */
	public static String getDay(){
		Calendar c=Calendar.getInstance();
		return format(c.getTime(),"yyyyMMdd");
	}
	
	/**
	 * 
	 * 功能：获取当前日格式dd
	 * 作者：Judy
	 * 时间：2011-5-7下午09:09:09
	 */
	public static String getDay2(){
		Calendar c=Calendar.getInstance();
		return format(c.getTime(),"dd");
	}
	/**
	 * 
	 * 功能：获取当前日格式xxxx年x月x日
	 * 作者：shibaoning
	 * 时间：2012-2-9 
	 *  @return 返回当前日期（2012年2月9日）
	 */
	public static String dateToString()
	{
	    Calendar c1 =  Calendar.getInstance() ;
	     String y =  c1.get(Calendar.YEAR)+"";
	     String m =  c1.get(Calendar.MONTH)+1+"";
	     String d =  c1.get(Calendar.DATE)+"";
		return  y+"年"+m+"月"+d+"日";
	}
	
	/**
	 * 日期校验
	 * @MethodName validateDate
	 * @Description 
	 * @Author zhouyun
	 * @CreateDate：2013-04-17
	 *
	 * @param beginTime 开始日期YYYYMMDD
	 * @param endTime　结束日期YYYYMMDD
	 * @return
	 */
	public static int validateDate(String beginTime,String endTime){
		BigDecimal temBeginTime = new BigDecimal(beginTime);
		BigDecimal  temEndTime = new BigDecimal(endTime);
		int i = temBeginTime.compareTo(temEndTime);//前者大于后者则返回1，等于返回0，小于返回-1
		return i;
	}
	
	/**
	 * 当前日期前的间隔天数日期
	 * 例如当前日期为20130510，间隔天数是3，则返回20130507
	 * @MethodName getDate
	 * @Description 
	 * @Author zhouyun
	 * @CreateDate：2013-04-17
	 *
	 * @param intervalDay 间隔天数
	 * @return YYYYMMDD
	 * @throws ParseException
	 */
	public static String getDate(int intervalDay) throws ParseException{
		Calendar currentDate = Calendar.getInstance();
		currentDate.setTime(new Date());
		currentDate.set(Calendar.DATE, currentDate.get(Calendar.DATE) - intervalDay);
		return new SimpleDateFormat("yyyyMMdd").format(currentDate.getTime()); 
	}
	
	/**
	 * 指定日期前的间隔天数日期
	 * 例如指定日期为20130510，间隔天数是3，则返回20130507
	 * @MethodName getDate
	 * @Description 
	 * @Author zhouyun
	 * @CreateDate：2013-04-17
	 *
	 * @param tempDate 指定日期 YYYYMMDD
	 * @param intervalDay 间隔天数
	 * @return YYYYMMDD
	 * @throws ParseException
	 */
	public static String getDate(String tempDate,int intervalDay) throws ParseException{
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date dt = df.parse(tempDate);
		Calendar beforDate = Calendar.getInstance();
		beforDate.setTime(dt);
		beforDate.set(Calendar.DATE, beforDate.get(Calendar.DATE) - intervalDay);
		return new SimpleDateFormat("yyyyMMdd").format(beforDate.getTime()); 
	}
	
	/**
	 * 字符串日期格式转换，YYYYMMDD->YYYY-MM-DD
	 * @MethodName formatDate
	 * @CreateDate Jun 25, 2013
	 * @Author ZHOUYUN
	 * @param dateStr 参数日期格式为YYYYMMDD
	 * @return 返回日期格式为YYYY-MM-DD
	 * @throws ParseException
	 */
	public static String formatDate(String dateStr) throws ParseException{
		Date date = new SimpleDateFormat("yyyyMMdd").parse(dateStr);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}
	
	/**
	 * 判断YYYYMMDD是否为有效日期
	 * @MethodName checkDateYYYYMMDD
	 * @Description 
	 * @Author zhouyun
	 * @CreateDate：2013-05-28
	 *
	 * @param dateStr YYYYMMDD
	 * @return
	 */
	public static boolean checkDateYYYYMMDD(String dateStr) {
		if (dateStr == null) {
			return false;
		}
		if (dateStr.length() != 8) {
			return false;
		}
		try {
			int yyyy = Integer.parseInt(dateStr.substring(0, 4));
			int mm = Integer.parseInt(dateStr.substring(4, 6));
			int dd = Integer.parseInt(dateStr.substring(6, 8));
			if ((yyyy < 2000) || (yyyy > 2500)) {
				return false;
			}
			if ((mm < 1) || (mm > 12)) {
				return false;
			}
			if ((dd < 1) || (dd > 31)) {
				return false;
			}
			switch (mm) {
			case 1:
				if (dd < 32) {
					return true;
				}
				break;
			case 2:
				if ((yyyy % 4) == 0) {
					if (dd < 30) {
						return true;
					}
				} else {
					if (dd < 29) {
						return true;
					}
				}
				break;
			case 3:
				if (dd < 32) {
					return true;
				}
				break;
			case 4:
				if (dd < 31) {
					return true;
				}
				break;
			case 5:
				if (dd < 32) {
					return true;
				}
				break;
			case 6:
				if (dd < 31) {
					return true;
				}
				break;
			case 7:
				if (dd < 32) {
					return true;
				}
				break;
			case 8:
				if (dd < 32) {
					return true;
				}
				break;
			case 9:
				if (dd < 31) {
					return true;
				}
				break;
			case 10:
				if (dd < 32) {
					return true;
				}
				break;
			case 11:
				if (dd < 31) {
					return true;
				}
				break;
			case 12:
				if (dd < 32) {
					return true;
				}
				break;
			default:
				return false;
			}

		} catch (NumberFormatException e) {
			return false;
		}
		return false;
	}
	

	/**
	 * 验证是否为日期格式
	 * @param checkValue   yyyyMMdd HH:mm:ss  yyyy/MM/dd HH:mm:ss  yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static boolean isDate(String checkValue){
		String eL = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-9]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(checkValue);
		return m.matches();
	}
	
	/**
	 * 传入的时间和当天时间比
	 * @param date 要比较的时间
	 * @return true：传入时间小于当前时间  false：传入时间大于等于当前时间
	 */
	public static boolean comparDate(Date date){
		Date d = new Date();
		String d1 = format(d,"yyyyMMdd");
		Date d2 = parse(d1,"yyyyMMdd");
	    if(date.compareTo(d2)<0){
	    	return true;
	    }    
		return false;
	}

	public static void main(String[] args) throws Exception {
		//System.out.println(monthToDate(6));
		//System.out.println(CommonParaHelpUtil.getSysDate());
		System.out.println(getDate(180));
		System.out.println(getDate("20130310",10));
		//System.out.println(" => "+ DateTimeUtils.getIntervalDays(DateTimeUtils.addDays(DateTimeUtils.parseDate(CommonParaHelpUtil.getSysDate()),0),DateTimeUtils.addHours(DateTimeUtils.parseDate(CommonParaHelpUtil.getSysDate()),1)));
		    String DateStr1 = "2011-01-9 10:20:16";  
	        String DateStr2 = "2011-10-07 15:50:35";  
	        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	        Date dateTime1 = dateFormat.parse(DateStr1);  
	        Date dateTime2 = dateFormat.parse(DateStr2);  
	        int i = dateTime1.compareTo(dateTime2);  
	        System.out.println(i);
	        System.out.println(i < 0);  
	}
}
