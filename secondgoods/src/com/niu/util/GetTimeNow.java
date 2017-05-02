package com.niu.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetTimeNow {
	public static String getTime(){
		Date date = new Date(); //获取当前的系统时间。
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss") ; //使用了默认的格式创建了一个日期格式化对象。
		String time = dateFormat.format(date);  //可以把日期转换转指定格式的字符串
		return time;
	}
}
