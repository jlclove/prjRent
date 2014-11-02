package com.dyrent.utils;

import com.dooioo.webplus.httpclient.HttpClientPoolManager;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

/**
 * Created by hqm on 14-5-12.
 */
public class HttpClientUtil {
	private static final String DEFAULT_CHARSET = "utf-8";

	/**
	 * @since: 1.0.2
	 * @param url
	 * @return
	 * @throws java.io.IOException
	 *             <p>
	 *             响应数据根据Server端返回的content-type自动进行转码,如果server端没有返回charset,
	 *             则使用ISO-8859-1 所有请求共享一个个httpclient
	 *             </p>
	 */
	public static String doGet(String url) throws IOException {
		return doGet(url, "");
	}

	public static String doGet(String url, Cookie[] cookies) throws IOException {
		return doGet(url, "", cookies);
	}

	public static String doGet(String url, int tryCount, long waitingStamp)
			throws IOException {
		return doGet(url, "", tryCount, waitingStamp);
	}

	public static String doGet(String url, String charset, int tryCount,
			long waitingStamp) throws IOException {
		return doGet(url, "", charset, tryCount, waitingStamp, null);
	}

	public static String doGet(String url, String charset, int tryCount,
			long waitingStamp, Cookie[] cookies) throws IOException {
		return doGet(url, "", charset, tryCount, waitingStamp, cookies);
	}

	/**
	 * @since: 1.0.2
	 * @param url
	 * @param params
	 *            例如： a=3&b=4
	 * @return
	 * @throws IOException
	 *             <p>
	 *             响应数据根据Server端返回的content-type自动进行转码,如果server端没有返回charset,
	 *             则使用ISO-8859-1 所有请求共享一个httpclient
	 *             </p>
	 */
	public static String doGet(String url, Map<String, String> params)
			throws IOException {
		if (params != null && !params.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for (Map.Entry<String, String> pair : params.entrySet()) {
				sb.append(pair.getKey() + "=" + pair.getValue() + "&");
			}
			sb.deleteCharAt(sb.length() - 1);
			return doGet(url, sb.toString());
		}
		return doGet(url, "");
	}

	public static String doGet(String url, String params) throws IOException {
		return doGet(url, params, 0, 0);
	}

	public static String doGet(String url, String params, Cookie[] cookies)
			throws IOException {
		return doGet(url, params, 0, 0, cookies);
	}

	/**
	 * @since: 1.0.2
	 * @param url
	 * @throws IOException
	 *             <p>
	 *             响应数据根据Server端返回的content-type自动进行转码,如果server端没有返回charset,
	 *             则使用ISO-8859-1 所有请求共享一个httpclient
	 *             </p>
	 */
	public static String doGet(String url, String params, String charset,
			int tryCount, long waitingStamp, Cookie[] cookies)
			throws IOException {
		String result = StringUtils.EMPTY;
		for (int i = 0; i <= tryCount; i++) {
			try {
				result = invokeGet(url, params, charset, HttpClientPoolManager
						.getInstance().sigletonClient(), cookies);
				break;
			} catch (IOException e) {
				try {
					Thread.sleep(waitingStamp);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				continue;
			}
		}
		if (StringUtils.isNotBlank(result)) {
			return result;
		} else {
			throw new IOException();
		}
	}

	/**
	 * since: 1.0.2
	 * 
	 * @param url
	 * @param params
	 *            ,参数map，默认使用utf-8编码
	 * @return <p>
	 *         响应数据根据Server端返回的content-type自动进行转码,如果server端没有返回charset,则使用ISO-
	 *         8859-1 所有请求共享一个httpclient
	 *         </p>
	 */
	public static String doPost(String url, Map<String, String> params)
			throws IOException {
		return doPost(url, params, DEFAULT_CHARSET);
	}

	/**
	 * @since: 1.0.2
	 * @param url
	 * @return
	 * @throws IOException
	 *             <p>
	 *             响应数据根据Server端返回的content-type自动进行转码,如果server端没有返回charset,
	 *             则使用ISO-8859-1 所有请求共享一个httpclient
	 *             </p>
	 */
	public static String doPost(String url) throws IOException {
		return doPost(url, null, DEFAULT_CHARSET);
	}

	/**
	 * @since: 1.0.2
	 * @param url
	 * @param params
	 *            请求参数
	 * @param encodeCharset
	 *            参数编码格式
	 * @return
	 * @throws IOException
	 *             <p>
	 *             响应数据根据Server端返回的content-type自动进行转码,如果server端没有返回charset,
	 *             则使用ISO-8859-1 post request,所有请求共享一个httpclient
	 *             </p>
	 */
	public static String doPost(String url, Map<String, String> params,
			String encodeCharset) throws IOException {
		return invokePost(url, params, encodeCharset, HttpClientPoolManager
				.getInstance().sigletonClient());
	}

	/**
	 * @since: 1.0.2
	 * @param url
	 * @param params
	 *            ,例如：a=3&b=4
	 * @return
	 * @throws IOException
	 *             <p>
	 *             响应数据根据Server端返回的content-type自动进行转码,如果server端没有返回charset,
	 *             则使用ISO-8859-1
	 *             使用HttpClientPoolManager托管httpclient,每次请求生成一个httpclient
	 *             </p>
	 */
	public static String newGet(String url, String params) throws IOException {
		return invokeGet(url, params, DEFAULT_CHARSET, HttpClientPoolManager
				.getInstance().newWrappedClient(), null);
	}

	/**
	 * @since: 1.0.2
	 * @param url
	 * @return
	 * @throws IOException
	 *             <p>
	 *             响应数据根据Server端返回的content-type自动进行转码,如果server端没有返回charset,
	 *             则使用ISO-8859-1
	 *             使用HttpClientPoolManager托管httpclient,每次请求生成一个httpclient
	 *             </p>
	 */
	public static String newGet(String url) throws IOException {
		return newGet(url, "");
	}

	/**
	 * @since: 1.0.2
	 * @param url
	 * @param params
	 *            ,请求参数
	 * @return
	 * @throws IOException
	 *             <p>
	 *             响应数据根据Server端返回的content-type自动进行转码,如果server端没有返回charset,
	 *             则使用ISO-8859-1
	 *             使用HttpClientPoolManager托管httpclient,每次请求生成一个httpclient
	 *             </p>
	 */
	public static String newGet(String url, Map<String, String> params)
			throws IOException {
		if (params != null && !params.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for (Map.Entry<String, String> pair : params.entrySet()) {
				sb.append(pair.getKey() + "=" + pair.getValue() + "&");
			}
			sb.deleteCharAt(sb.length() - 1);
			return newGet(url, sb.toString());
		}
		return newGet(url, "");
	}

	/**
	 * since: 1.0.2
	 * 
	 * @param url
	 * @param params
	 *            ，请求参数,默认使用utf-8编码
	 * @return <p>
	 *         响应数据根据Server端返回的content-type自动进行转码,如果server端没有返回charset,则使用ISO-
	 *         8859-1 post
	 *         request,使用HttpClientPoolManager托管httpclient,每次请求生成一个httpclient
	 *         </p>
	 */
	public static String newPost(String url, Map<String, String> params)
			throws IOException {
		return newPost(url, params, DEFAULT_CHARSET);
	}

	/**
	 * @since: 1.0.2
	 * @param url
	 * @return
	 * @throws IOException
	 *             <p>
	 *             响应数据根据Server端返回的content-type自动进行转码,如果server端没有返回charset,
	 *             则使用ISO-8859-1 post
	 *             request,使用HttpClientPoolManager托管httpclient
	 *             ,每次请求生成一个httpclient
	 *             </p>
	 */
	public static String newPost(String url) throws IOException {
		return newPost(url, null, DEFAULT_CHARSET);
	}

	/**
	 * @since: 1.0.2
	 * @param url
	 * @param params
	 *            请求参数
	 * @param encodeCharset
	 *            参数字符编码方式
	 * @return
	 * @throws IOException
	 *             <p>
	 *             响应数据根据Server端返回的content-type自动进行转码,如果server端没有返回charset,
	 *             则使用ISO-8859-1 post
	 *             request,使用HttpClientPoolManager托管httpclient
	 *             ,每次请求生成一个httpclient
	 *             </p>
	 */
	public static String newPost(String url, Map<String, String> params,
			String encodeCharset) throws IOException {
		return invokePost(url, params, encodeCharset, HttpClientPoolManager
				.getInstance().newWrappedClient());
	}

	/**
	 * @since: 1.0.2
	 * @param url
	 *            url
	 * @param param
	 *            请求参数
	 * @param httpClient
	 *            httpclient
	 * @return
	 * @throws IOException
	 *             <p>
	 *             调用httpget
	 *             响应数据根据Server端返回的content-type自动进行转码,如果server端没有返回charset
	 *             ,则使用ISO-8859-1
	 *             </p>
	 */
	private static String invokeGet(String url, String param, String charset,
			DefaultHttpClient httpClient, Cookie[] cookies) throws IOException {
		if (url == null || url.trim().isEmpty()) {
			return newJsonResult("fail", "url 不能为空。");
		}
		if (param != null && !param.isEmpty()) {
			url += (param.startsWith("?") ? "" : "?") + param;
		}
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
				60000);
		HttpGet httpGet = new HttpGet(url);
		// 请求中添加 cookie
		if (cookies != null && cookies.length > 0) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < cookies.length; i++) {
				sb.append(cookies[i].getName() + "=" + cookies[i].getValue()
						+ ";");
			}
			httpGet.setHeader("Cookie", sb.toString());
		}
		try {
			HttpResponse response = httpClient.execute(httpGet);
			InputStream instreams = response.getEntity().getContent();
			return convertStreamToString(instreams, charset);
		} catch (IOException e) {
			// 取消请求
			throw new IOException("httpclint 请求失败，url=" + url, e);
		} finally {
			httpGet.abort();
			httpGet.releaseConnection();
			httpClient.getConnectionManager().closeExpiredConnections();
		}
	}

	public static String convertStreamToString(InputStream is, String charset) {
		StringBuilder sb1 = new StringBuilder();
		byte[] bytes = new byte[4096];
		int size = 0;

		try {
			while ((size = is.read(bytes)) > 0) {
				String str = new String(bytes, 0, size,
						StringUtils.isBlank(charset) ? DEFAULT_CHARSET
								: charset);
				sb1.append(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb1.toString();
	}

	/**
	 * @since: 1.0.2
	 * @param url
	 * @param params
	 * @param charset
	 *            参数字符编码方式
	 * @param httpClient
	 * @return
	 * @throws IOException
	 *             <p>
	 *             调用http post
	 *             响应数据根据Server端返回的content-type自动进行转码,如果server端没有返回charset
	 *             ,则使用ISO-8859-1
	 *             </p>
	 */
	private static String invokePost(String url, Map<String, String> params,
			String charset, DefaultHttpClient httpClient) throws IOException {
		if (url == null || url.trim().isEmpty()) {
			return newJsonResult("fail", "url 不能为空。");
		}
		if (charset == null || charset.isEmpty()) {
			charset = DEFAULT_CHARSET;
		}
		httpClient.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
		HttpPost postMethod = new HttpPost(url);
		try {
			if (params != null) {
				List<NameValuePair> nvs = new ArrayList<NameValuePair>();
				for (Map.Entry<String, String> pair : params.entrySet()) {
					nvs.add(new BasicNameValuePair(pair.getKey(), pair
							.getValue()));
				}
				postMethod.setEntity(new UrlEncodedFormEntity(nvs, charset));
			}
			// 创建响应处理器处理服务器响应内容
			return httpClient.execute(postMethod, new BasicResponseHandler());
		} catch (IOException e) {
			postMethod.abort();
			throw new IOException("httpclint post 请求失败，url=" + url, e);
		} finally {
			postMethod.releaseConnection();
		}
	}

	private static String newJsonResult(String status, String message) {
		return "{\"status\":\"" + status + "\",\"message\":\"" + message
				+ "\"}";
	}
}
