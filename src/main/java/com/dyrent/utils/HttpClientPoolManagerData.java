package com.dyrent.utils;

import java.util.concurrent.ScheduledExecutorService;

import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.HttpParams;

import com.dyrent.context.HttpClientExecutorContext;


public class HttpClientPoolManagerData {
	public DefaultHttpClient provider;
	public HttpClientExecutorContext defaultContext;
	public PoolingClientConnectionManager defaultManager;
	public ScheduledExecutorService scheduler;
	public HttpParams defaultParams;
	public HttpRequestRetryHandler defaultRetryHandler;

	public HttpClientPoolManagerData() {
	}
}