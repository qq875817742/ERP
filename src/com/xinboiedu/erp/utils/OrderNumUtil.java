package com.xinboiedu.erp.utils;

public class OrderNumUtil {
	
	public static int serNum=1;
	public static int len=5;
	private static final byte[]zeros={48,48,48,48,48,48};
	
	public static String generatorOrderNum(){
		//日期
		String fir = FormatterUtil.FormaterDate2(System.currentTimeMillis());
		//数量
		int num=serNum++;
		int len2=(num+"").length();
		String sec=new String(zeros,0,len-len2);
		
		
		return Long.toHexString(new Long(fir+sec+num)).toUpperCase();
	}
}
