package com.dyrent.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.log4j.Logger;

import com.dyrent.context.HttpClientExecutorContext;
import com.dyrent.factory.DaemonThreadFactory;
import com.dyrent.helper.TypeHelper;


/**
 * @author "liuhui" create At 2013-12-18 上午9:54:08
 * @since httpclient4.2
 * 
 *        httpclient4.2 连接池管理
 */
public class HttpClientPoolManager {

	private static final Logger logger = Logger
			.getLogger(HttpClientPoolManager.class);
	private DefaultHttpClient provider;
	private HttpClientExecutorContext defaultContext;
	private PoolingClientConnectionManager defaultManager;
	private ScheduledExecutorService scheduler;
	private HttpParams defaultParams;
	private HttpRequestRetryHandler defaultRetryHandler;

	private static class SigletonHolder {
		private static HttpClientPoolManager defaultManager = new HttpClientPoolManager();
	}

	private HttpClientPoolManager() {
		// 初始化环境
		initContext();
		// 设置默认http params
		initDefaultHttpParams();
		// 生成默认manager
		initManager();
		// 生成retry handler
		initRetryHandler();
		// 初始化全局共享httpclient
		initDefaultClient();
	}

	/**
	 * @since: 1.0.3
	 * @param provider
	 *            <p>
	 *            新增gzip拦截器
	 *            </p>
	 */
	public void addResponseInterceptors(DefaultHttpClient provider) {
		provider.addResponseInterceptor(new HttpResponseInterceptor() {
			public void process(final HttpResponse response,
					final HttpContext context) throws HttpException,
					IOException {
				// 获取内容编码
				Header ceheader = response.getEntity().getContentEncoding();
				if (ceheader != null) {
					for (HeaderElement element : ceheader.getElements()) {
						if (element.getName().equalsIgnoreCase("gzip")) {
							response.setEntity(new GzipDecompressingEntity(
									response.getEntity()));
							return;
						}
					}
				}
			}
		});
	}

	private void initDefaultClient() {
		provider = new DefaultHttpClient(this.defaultManager,
				this.defaultParams);
		provider.setHttpRequestRetryHandler(defaultRetryHandler);
		this.addResponseInterceptors(provider);
	}

	/**
	 * since: 1.0.2
	 * <p>
	 * 实例化request重试机制
	 * </p>
	 * 
	 */
	private void initRetryHandler() {
		// 使用默认请求重试
		defaultRetryHandler = new DefaultHttpRequestRetryHandler(
				this.defaultContext.getRetryTimes(), false);
	}

	private void initContext() {
		Properties config = new Properties();
		if (logger.isInfoEnabled()) {
			logger.info("ExecutorContext 开始初始化，加载 webplus/httpclient.properties...........");
		}
		InputStream is = null;
		try {
			is = HttpClientPoolManager.class
					.getResourceAsStream("/webplus/httpclient.properties");
			config.load(is);
		} catch (IOException e) {
			throw new IllegalArgumentException("加载 httpclient.properties 出错。",
					e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		this.defaultContext = new HttpClientExecutorContext();
		this.defaultContext.setDefaultRoute(TypeHelper.defaultIntQuietly(
				config.getProperty("httpclient.pool.connection.route"),
				HttpClientExecutorContext.DEFAULT_MAX_PER_ROUTE));
		this.defaultContext.setMaxConnection(TypeHelper.defaultIntQuietly(
				config.getProperty("httpclient.pool.connection.max"),
				HttpClientExecutorContext.DEFAULT_MAX_CONNECTION));
		this.defaultContext.setHttpRegistryPort(TypeHelper.defaultIntQuietly(
				config.getProperty("httpclient.pool.registry.http.port"),
				HttpClientExecutorContext.DEFAULT_HTTP_PORT));
		this.defaultContext.setHttpsRegistryPort(TypeHelper.defaultIntQuietly(
				config.getProperty("httpclient.pool.registry.https.port"),
				HttpClientExecutorContext.DEFAULT_HTTPS_PORT));

		// 设置http params
		this.defaultContext.setHttpParamConnectionTimeout(TypeHelper
				.defaultIntegerQuietly(config
						.getProperty("httpclient.param.connectionTimeout")));
		this.defaultContext.setHttpParamReadTimeout(TypeHelper
				.defaultIntegerQuietly(config
						.getProperty("httpclient.param.readTimeout")));
		this.defaultContext.setHttpParamSocketBufferSize(TypeHelper
				.defaultIntegerQuietly(config
						.getProperty("httpclient.param.socketBufferSize")));
		this.defaultContext.setHttpParamSoKeepAlive(TypeHelper
				.defaultBoolean(config
						.getProperty("httpclient.param.soKeepAlive")));
		this.defaultContext.setHttpParamTcpNoDelay(TypeHelper
				.defaultBoolean(config
						.getProperty("httpclient.param.tcpNoDelay")));

		// 设置守护线程扫描间隔
		this.defaultContext.setSchedulerInitDelay(TypeHelper.defaultIntQuietly(
				config.getProperty("httpclient.scheduler.initDelay"),
				HttpClientExecutorContext.DEFAULT_SCHEDULER_INIT_DELAY));
		this.defaultContext.setSchedulerPeriod(TypeHelper.defaultIntQuietly(
				config.getProperty("httpclient.scheduler.period"),
				HttpClientExecutorContext.DEFAULT_SCHEDULER_PERIOD));

		// 设置retry次数
		this.defaultContext.setRetryTimes(TypeHelper.defaultIntQuietly(
				config.getProperty("httpclient.retry.times"),
				HttpClientExecutorContext.DEFAULT_RETRY_TIMES));

		logger.info("HttpClientExecutorContext initialed....配置参数："
				+ this.defaultContext.toString());
	}

	/**
	 * since: 1.0.2 初始化Manager。
	 */
	private void initManager() {
		if (this.defaultContext == null) {
			throw new IllegalArgumentException(
					"请初始化HttpClientExecutorContext ，请检查webplus/httpclient.properties是否存在。");
		}
		if (logger.isInfoEnabled()) {
			logger.info("HttpClientPoolManager initialing............");
		}
		SchemeRegistry registry = new SchemeRegistry();
		registry.register(new Scheme("http", this.defaultContext
				.getHttpRegistryPort(), PlainSocketFactory.getSocketFactory()));
		registry.register(new Scheme("https", this.defaultContext
				.getHttpsRegistryPort(), SSLSocketFactory.getSocketFactory()));

		defaultManager = new PoolingClientConnectionManager(registry);
		defaultManager.setDefaultMaxPerRoute(this.defaultContext
				.getDefaultRoute());
		defaultManager.setMaxTotal(this.defaultContext.getMaxConnection());
		if (logger.isInfoEnabled()) {
			logger.info("启动守护线程，每隔"
					+ this.defaultContext.getHttpParamReadTimeout() * 2
					+ "（毫秒）定时清理闲置的连接。。。。");
		}
		// 定时清理闲置或者2*this.httpParamReadTimeout 读取超时时间
		scheduler = Executors
				.newScheduledThreadPool(1, new DaemonThreadFactory(
						"httpClientpoolingManager-con-monitor"));
		scheduler
				.scheduleAtFixedRate(new IdleConnectionMonitor(defaultManager,
						this.defaultContext.getHttpParamReadTimeout()),
						this.defaultContext.getSchedulerInitDelay(),
						this.defaultContext.getSchedulerPeriod(),
						TimeUnit.MILLISECONDS);
	}
	
	/**
	 * since: 1.0.2
	 * <p>
	 * 设置默认http参数
	 * <p>
	 */
	private void initDefaultHttpParams() {
		if (logger.isInfoEnabled()) {
			logger.info("DefaultParams initialing.......");
		}
		defaultParams = new BasicHttpParams();
		if (this.defaultContext.getHttpParamConnectionTimeout() != null) {
			defaultParams.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,
					this.defaultContext.getHttpParamConnectionTimeout());
		}
		if (this.defaultContext.getHttpParamReadTimeout() != null) {
			defaultParams.setParameter(CoreConnectionPNames.SO_TIMEOUT,
					this.defaultContext.getHttpParamReadTimeout());
		}
		if (this.defaultContext.getHttpParamTcpNoDelay() != null) {
			defaultParams.setParameter(CoreConnectionPNames.TCP_NODELAY,
					this.defaultContext.getHttpParamTcpNoDelay());
		}
		if (this.defaultContext.getHttpParamSocketBufferSize() != null) {
			defaultParams.setParameter(CoreConnectionPNames.SOCKET_BUFFER_SIZE,
					this.defaultContext.getHttpParamSocketBufferSize());
		}
		if (this.defaultContext.getHttpParamSoKeepAlive() != null) {
			defaultParams.setParameter(CoreConnectionPNames.SO_KEEPALIVE,
					this.defaultContext.getHttpParamSoKeepAlive());
		}
	}

	/**
	 * since: 1.0.2
	 * 
	 * @return <p>
	 *         根据httpclient.properties设置了http.param
	 *         requestRetry次数默认不重试，取决于httpclient.properties
	 *         的httpclient.retry.times= 响应有gzip拦截器
	 *         </p>
	 */
	public DefaultHttpClient newWrappedClient() {
		DefaultHttpClient client = new DefaultHttpClient(this.defaultManager,
				this.defaultParams);
		client.setHttpRequestRetryHandler(this.defaultRetryHandler);
		this.addResponseInterceptors(client);
		return client;
	}

	/**
	 * @since: 1.0.2
	 * @return <p>
	 *         全局共享一个httpclient 响应有gzip拦截器
	 *         </p>
	 */
	public DefaultHttpClient sigletonClient() {
		return provider;
	}

	/**
	 * since: 1.0.2
	 * 
	 * @return
	 * @see newWrappedClient()
	 *      <p>
	 *      没有设置已配置的http params 由PoolingClientConnectionManager管理的HttpClient
	 *      requestRetry次数默认不重试，取决于httpclient.properties
	 *      的httpclient.retry.times= 响应有gzip拦截器
	 *      </p>
	 */
	public DefaultHttpClient newPooledClient() {
		DefaultHttpClient client = new DefaultHttpClient(this.defaultManager);
		client.setHttpRequestRetryHandler(this.defaultRetryHandler);
		this.addResponseInterceptors(client);
		return client;
	}

	/**
	 * @since: 1.0.2
	 * @return <p>
	 *         获取配置信息
	 *         </p>
	 */
	public String getConfigInfo() {
		return this.defaultContext.toString();
	}

	/**
	 * since: 1.0.2 关闭定时清理连接的监控
	 */
	public void shutdownMonitor() {
		if (scheduler != null) {
			scheduler.shutdown();
		}
	}

	/**
	 * since: 1.0.2
	 * 
	 * @return 返回配置的httpclient连接池
	 * 
	 */
	public static HttpClientPoolManager getInstance() {
		return SigletonHolder.defaultManager;
	}

	/**
	 * 定时清理闲置或者过期的连接
	 * 
	 * @author "liuhui" create At 2013-12-18 上午11:13:25
	 */
	protected static class IdleConnectionMonitor implements Runnable {
		private PoolingClientConnectionManager manager;
		private int readTimeout;

		IdleConnectionMonitor(PoolingClientConnectionManager manager,
				int readTimeout) {
			this.manager = manager;
			this.readTimeout = readTimeout;
		}

		public void run() {
			if (logger.isInfoEnabled()) {
				logger.info("[httpclient]release start connect count:="
						+ manager.getTotalStats().getAvailable());
			}
			// Close expired connections
			manager.closeExpiredConnections();
			// Optionally, close connections
			// that have been idle longer than readTimeout*2 MILLISECONDS
			manager.closeIdleConnections(this.readTimeout * 2L,
					TimeUnit.MILLISECONDS);
			if (logger.isInfoEnabled()) {
				logger.info("release end connect count:="
						+ manager.getTotalStats().getAvailable());
			}
		}
	}
}
