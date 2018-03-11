package com.xinboiedu.erp.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class FormatterUtil {
	public static String FormaterDate(Long time){
		if(time==null){
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date(time));
	}
	public static String FormaterDate2(Long time){
		if(time==null){
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(new Date(time));
	}
	public static String FormaterTimeStamp(Long time){
		if(time==null){
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date(time));
	}
	public static String FormaterMoney(Double money){
		if(money==null){
			return "";
		}
		DecimalFormat decimalFormat = new DecimalFormat("#.00");				
		return decimalFormat.format(money);
	}
	
}
