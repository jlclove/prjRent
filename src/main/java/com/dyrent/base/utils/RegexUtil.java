package com.dyrent.base.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/** 
 * 正则 Util类
 * @author Jail    E -Mail:86455@ dooioo.com 
 */
public class RegexUtil {
	/**
	 * 标准格式为数字以","号间隔，过滤所有非正常字符，过滤头尾","号
	 * @param input 输入的字符串
	 * @return 合法的字符串，不会有null
	 */
	public static String replaceIllegalChars(String input){
		input = input==null?"":input;
		return StringUtil.trim(input.replaceAll("[^\\d,]", "").replaceAll(",+", ","),",");
	}
	
	/**
	 * 标准格式为中文以","号间隔，过滤所有非正常字符，过滤头尾","号
	 * @param input 输入的字符串
	 * @return 合法的字符串，不会有null，中文两本以"'"包裹，如 '胡杰','胡伟'
	 */
	public static String replaceIllegalCharsNoChinese(String input){
		input = input==null?"":input;
		input = StringUtil.trim(input.replaceAll("[^,\u4e00-\u9fa5]", "").replaceAll(",+", ","),",").replaceAll(",+", "','");
		return input.length()>0 ? ("'" + input + "'") : input;
	}
	
	/**
	 * 获得当前url的主域名,如 .dooioo.com,.dooioo.net
	 * @param url 当前的url地址
	 * @return 主域名
	 */
	public static String getDomain(String url){
		Pattern patt = Pattern.compile("[\\S]*?\\.([\\w-]+\\.(com|net|cn|gov|cn|org|name|info|biz|cc|tv|me|co|so|tel|mobi|asia))[\\S]*",Pattern.CASE_INSENSITIVE);
		Matcher match = patt.matcher(url);
		if(match.matches()){
			return match.group(1);
		}
		return "";
	}
	
	/**
	 * 获得当前url的主域名,如 .dooioo.com,.dooioo.net
	 * @param url 当前的url地址
	 * @return 主域名
	 */
	public static String getDooiooDomain(String url){
		Pattern patt = Pattern.compile("[\\S]*?\\.((dooioo|iderong)\\.(com|net|cn|gov|cn|org|name|info|biz|cc|tv|me|co|so|tel|mobi|asia))[\\S]*",Pattern.CASE_INSENSITIVE);
		Matcher match = patt.matcher(url);
		if(match.matches()){
			return match.group(2);
		}
		return "";
	}
	
	/**
	 * 检查指定text中是否存在参数字段，如 {0}{1}
	 * @param text 需要检查的text
	 * @return boolean
	 */
	public static boolean checkParametersExists(String text){
		return text.matches(".*?\\{\\d+\\}.*?");
	}
}
  
    