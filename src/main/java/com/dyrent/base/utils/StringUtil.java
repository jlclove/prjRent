package com.dyrent.base.utils;
/** 
 * StringBuilder Helper类
 * @author Jail    E -Mail:86455@ dooioo.com 
 */
public final class StringUtil {
	/**
	 * 去除String的首尾一个匹配字符(串)
	 * @param input String对象
	 * @param c 需要去除的尾部字符(串)
	 * @return 去除尾部指定字符(串)之后的String对象
	 */
	public static String trim(String input,String c){
		if(input.startsWith(c))
			input = input.substring(input.indexOf(c) + c.length());
		if(input.endsWith(c))
			input = input.substring(0, input.lastIndexOf(c));
		return input;
	}
}
  
    