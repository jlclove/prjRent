package com.dyrent.helper;

/**
 * @author "liuhui" create At 2013-12-18 下午1:10:36 类型处理辅助类
 */
public abstract class TypeHelper {
	// private static final Logger logger=Logger.getLogger(TypeHelper.class);
	/**
	 * since: 2013-12-18
	 * 
	 * @param value
	 * @param defaultValue
	 * @return object==null 或者 object.toString 转换为Integer报错则返回defaultValue
	 */
	public static int defaultIntQuietly(Object value, int defaultValue) {
		if (value == null) {
			return defaultValue;
		}
		return Integer.parseInt(value.toString());
	}

	/**
	 * since: 2013-12-18
	 * 
	 * @param value
	 * @return object==null 或者 object.toString 转换为Integer报错则返回null
	 */
	public static Integer defaultIntegerQuietly(Object value) {
		if (value == null) {
			return null;
		}
		try {
			return Integer.valueOf(value.toString());
		} catch (Exception e) {
			// logger.error("类型转换错误。", e);
			return null;
		}
	}

	/**
	 * since: 2013-12-18
	 * 
	 * @param value
	 * @return object==null 返回 defaultValue，其他情况 返回 Boolean.valueOf(String)
	 */
	public static boolean defaultBool(Object value, boolean defaultValue) {
		if (value == null || value.toString().trim().isEmpty()) {
			return defaultValue;
		}
		return Boolean.parseBoolean(value.toString());
	}

	/**
	 * since: 2013-12-18
	 * 
	 * @param value
	 * @return object==null 或者 object.toString 转换为Integer报错则返回null
	 */
	public static Boolean defaultBoolean(Object value) {
		if (value == null || value.toString().trim().isEmpty()) {
			return Boolean.FALSE;
		}
		return Boolean.valueOf(value.toString());
	}
}
