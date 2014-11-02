package com.dyrent.context;

/**
 * @author "liuhui" create At 2013-12-18 下午12:45:06 http 默认执行环境
 */
public class HttpClientExecutorContext {
	/**
	 * 每个连接最大路由数
	 */
	public static final int DEFAULT_MAX_PER_ROUTE = 10;
	/**
	 * 最大连接数
	 */
	public static final int DEFAULT_MAX_CONNECTION = 100;

	/**
	 * 默认http port
	 */
	public static final int DEFAULT_HTTP_PORT = 80;

	/**
	 * 默认 https port
	 */
	public static final int DEFAULT_HTTPS_PORT = 443;

	/**
	 * 默认守护线程1分钟后启动。
	 */
	public static final int DEFAULT_SCHEDULER_INIT_DELAY = 60 * 1000;

	/**
	 * 默认守护线程20分钟扫描一次
	 */
	public static final int DEFAULT_SCHEDULER_PERIOD = 20 * 60 * 1000;

	/**
	 * 默认不重试
	 */
	public static final int DEFAULT_RETRY_TIMES = 0;
	/**
	 * http port
	 */
	private int httpRegistryPort;
	/**
	 * https port
	 */
	private int httpsRegistryPort;
	/**
	 * http 最大连接数
	 */
	private int maxConnection;
	/**
	 * 默认路由数
	 */
	private int defaultRoute;
	/**
	 * socket读取超时时间
	 */
	private Integer httpParamReadTimeout;
	/**
	 * socket连接超时时间
	 */
	private Integer httpParamConnectionTimeout;
	/**
	 * tcp no delay设置
	 */
	private Boolean httpParamTcpNoDelay;
	/**
	 * socket buffer size
	 */
	private Integer httpParamSocketBufferSize;
	private Boolean httpParamSoKeepAlive;

	private int retryTimes;
	private int schedulerInitDelay;
	private int schedulerPeriod;

	public HttpClientExecutorContext() {
		super();
	}

	public int getHttpRegistryPort() {
		return httpRegistryPort;
	}

	public int getHttpsRegistryPort() {
		return httpsRegistryPort;
	}

	public int getMaxConnection() {
		return maxConnection;
	}

	public int getDefaultRoute() {
		return defaultRoute;
	}

	public Integer getHttpParamReadTimeout() {
		return httpParamReadTimeout;
	}

	public Integer getHttpParamConnectionTimeout() {
		return httpParamConnectionTimeout;
	}

	public Boolean getHttpParamTcpNoDelay() {
		return httpParamTcpNoDelay;
	}

	public int getRetryTimes() {
		return retryTimes;
	}

	public void setRetryTimes(int retryTimes) {
		this.retryTimes = retryTimes;
	}

	public Integer getHttpParamSocketBufferSize() {
		return httpParamSocketBufferSize;
	}

	public Boolean getHttpParamSoKeepAlive() {
		return httpParamSoKeepAlive;
	}

	public int getSchedulerInitDelay() {
		return schedulerInitDelay;
	}

	public int getSchedulerPeriod() {
		return schedulerPeriod;
	}

	public void setHttpRegistryPort(int httpRegistryPort) {
		this.httpRegistryPort = httpRegistryPort;
	}

	public void setHttpsRegistryPort(int httpsRegistryPort) {
		this.httpsRegistryPort = httpsRegistryPort;
	}

	public void setMaxConnection(int maxConnection) {
		this.maxConnection = maxConnection;
	}

	public void setDefaultRoute(int defaultRoute) {
		this.defaultRoute = defaultRoute;
	}

	public void setHttpParamReadTimeout(Integer httpParamReadTimeout) {
		this.httpParamReadTimeout = httpParamReadTimeout;
	}

	public void setHttpParamConnectionTimeout(Integer httpParamConnectionTimeout) {
		this.httpParamConnectionTimeout = httpParamConnectionTimeout;
	}

	public void setHttpParamTcpNoDelay(Boolean httpParamTcpNoDelay) {
		this.httpParamTcpNoDelay = httpParamTcpNoDelay;
	}

	public void setHttpParamSocketBufferSize(Integer httpParamSocketBufferSize) {
		this.httpParamSocketBufferSize = httpParamSocketBufferSize;
	}

	public void setHttpParamSoKeepAlive(Boolean httpParamSoKeepAlive) {
		this.httpParamSoKeepAlive = httpParamSoKeepAlive;
	}

	public void setSchedulerInitDelay(int schedulerInitDelay) {
		this.schedulerInitDelay = schedulerInitDelay;
	}

	public void setSchedulerPeriod(int schedulerPeriod) {
		this.schedulerPeriod = schedulerPeriod;
	}

	@Override
	public String toString() {
		return "HttpClientExecutorContext [httpRegistryPort="
				+ httpRegistryPort + ", httpsRegistryPort=" + httpsRegistryPort
				+ ", maxConnection=" + maxConnection + ", defaultRoute="
				+ defaultRoute + ", httpParamReadTimeout="
				+ httpParamReadTimeout + ", httpParamConnectionTimeout="
				+ httpParamConnectionTimeout + ", httpParamTcpNoDelay="
				+ httpParamTcpNoDelay + ", httpParamSocketBufferSize="
				+ httpParamSocketBufferSize + ", httpParamSoKeepAlive="
				+ httpParamSoKeepAlive + ", retryTimes=" + retryTimes
				+ ", schedulerInitDelay=" + schedulerInitDelay
				+ ", schedulerPeriod=" + schedulerPeriod + "]";
	}
}
