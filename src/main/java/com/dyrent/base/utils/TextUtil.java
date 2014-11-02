package com.dyrent.base.utils;

import org.apache.commons.lang3.StringUtils;


/** 
 * 文档 Util 类
 */
public class TextUtil {

	/**
	 * 过滤本方法内所有可以过滤的 <.*> &nbsp;\s \n\r\t 
	 * @param content 需要过滤的字符串
	 * @return 过滤后的字符串
	 */
	public static String replaceAll(String content){
		return replaceBlank(replaceHtmlTag(replaceWrap(content)));
	}
	
	/**
	 * 过滤所有的Html标签 <.*>
	 * @param content 需要过滤的字符串
	 * @return 过滤后的字符串
	 */
	public static String replaceHtmlTag(String content){
		return content.replaceAll("<[^>]*>", "").replaceAll("&nbsp;", "").replaceAll("[\n\r\t]","");
	}
	/**
	 * 过滤所有的空格 &nbsp;\s
	 * @param content 需要过滤的字符串
	 * @return 过滤后的字符串
	 */
	public static String replaceBlank(String content){
		return content.replaceAll("[&nbsp;\\s]", "").replaceAll("[\n\r\t]","");
	}
	
	/**
	 * 过滤所有的文本换行及缩进符 \n\r\t
	 * @param content 需要过滤的字符串
	 * @return 过滤后的字符串
	 */
	public static String replaceWrap(String content){
		return content.replaceAll("[\n\r\t]","");
	}
	
	/**
	 * 过滤换行符为html换行<br>
	 * @param content  需要过滤的字符串
	 * @return 过滤后的字符串
	 */
	public static String replaceWrapToHtml(String content){
		return content.replaceAll("\r\n", "<br/>");
	}
	
	/**
	 * 层级替换文本，使用方法如下：依次替换{0}{1}的参数为后面提供的参数，可多个使用比如：msgModel = {0}：{1},我是{0} , 参数： 胡杰，早上好 ==> 胡杰：早上好，我是胡杰
	 * @param msgModel 文本模版
	 * @param strings 替换的参数集合
	 * @return 替换后的文本
	 */
	public static String format(String msgModel,String...strings){
		for(int i = 0 ; i<strings.length;i++){
			msgModel = msgModel.replaceAll("\\{"+i+"\\}", strings[i]);
		}
		return msgModel;
	}
	
	/**
	 * 防注入过滤
	 * @param content 需要过滤的字符串
	 * @return 过滤后的字符串
	 */
	public static String skipHack(String content){
		String hackStr = "'|and|exec|insert|select|delete|update|count|\\*|%|;|or|-|\\+|,|mid|master|truncate|declare|char|chr";
		return content.replaceAll(hackStr, "");
	}
	/**
	 * 截取指定长度的字符串
	 * @param text 需要截取的字符串
	 * @param maxLength 指定长度
	 * @return
	 */
	public static String textSubstring(String text, int maxLength) {
		String result = text;
		if (StringUtils.isNotBlank(text) && text.length() > maxLength) {
			result = text.substring(0, maxLength - 1);
		}
		return result;
	}
}
  
    