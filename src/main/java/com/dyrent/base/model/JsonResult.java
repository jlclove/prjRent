package com.dyrent.base.model;

import java.util.HashMap;


/**
 * 返回json的标准格式
 * {status:'ok', mssage:'test', data:{}}
 *
 */
public class JsonResult extends HashMap<String, Object> {
	private static final long serialVersionUID = -2902697862980643408L;
	private static final String KEY_MESSAGE = "message";
	private static final String STATUS_MESSAGE = "status";

	public static enum STATUS {
		OK, FAIL
	};

	public static final JsonResult OK = new JsonResult(STATUS.OK);
	public static final JsonResult FAIL = new JsonResult(STATUS.FAIL);

	@SuppressWarnings("unused")
	private JsonResult() {
	}

	/**
	 * 构造包含 status 的JsonResult
	 *
	 * @param status
	 */
	public JsonResult(STATUS status) {
		this.put(STATUS_MESSAGE, status.name().toLowerCase());
	}

	public JsonResult(STATUS status, String message) {
		this(status);
		this.put(KEY_MESSAGE, message);
	}

	public JsonResult put(String key, Object value) {
		if (this == JsonResult.OK || this == JsonResult.FAIL) {
			JsonResult jr = (JsonResult) this.clone();
			jr.put(key, value);
			return jr;
		} else {
			super.put(key, value);
		}
		return this;
	}

	public JsonResult message(String message) {
		JsonResult result = (JsonResult) this.clone();
		result.put(KEY_MESSAGE, message);
		return result;
	}

	@Override
	public Object clone() {
		return super.clone();
	}
}
