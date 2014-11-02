package com.dyrent.common;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

import java.io.Serializable;

/**
 * 系统配置参数
 * @author Jail Hu
 * @description 
 * @date 2014年5月4日
 */
public class Configuration implements Serializable{
	private static final long serialVersionUID = -8557407685178026272L;
	private static Logger logger = Logger.getLogger(Configuration.class);
	/**
	 * 单例内部变量
	 */
	private static Configuration config;
	/**
	 * 当前环境，测试: development 生产： production
	 */
	private String env;
    /**
     * 版本号
     */
    private String version;
	/**
	 * 列表页默认的照片url
	 */
	private String default_photo_url;
	/**
	 * 二手房 面积筛选 最大值
	 */
	private int sale_acreage_max;
	/**
	 * 二手房 面积筛选 最小值
	 */
	private int sale_acreage_min;
	/**
	 * 二手房 价格筛选 最大值
	 */
	private int sale_price_max;
	/**
	 * 二手房 价格筛选 最小值
	 */
	private int sale_price_min;
    /**
     * 照片访问路径的url前缀
     */
    private String photoUrlPrefix;
    /**
     * 员工照片访问路径
     */
    private String employee_photo_url;
    /**
     * 默认没有显示的条数
     */
    private int defaultPageSize;

    /**
     * 关联房源价格浮动区间
     */
    private double priceFrom;

    /**
     * 关联房源价格浮动区间
     */
    private double priceTo;

    /**
     * 关联房源展示条数
     */
    private int unionFySize;
    /**
     * 人事招聘 主管工号，逗号隔开
     */
    private String receivers;
    /**
     * 发送邮件的url
     */
    private String send_email_url;
    /**
     * 发送邮件的内容
     */
    private String email_content;
    /**
     * pc 版域名
     */
    private String web_domain;
    /**
     * 手机 版域名
     */
    private String wap_domain;
	/**
	 * 获取分机号的url
	 */
	private String fenji_url;

    /*
     *转接号的初始值
     */
    private int startExtId;

    /*
     *转接号的最大值
     */
    private int maxExtId;

    /*
     *转接号临界值
     */
    private int criticalValue;
	/**
	 * 邮件抄送人
	 */
	private String copiesTo;

	/**
	 * 私有化默认构造函数
	 */
	private Configuration(){

	}

    /**
	 * 获得单例
	 * @return
	 */
	public synchronized static Configuration getInstance(){
		if(config == null){
			config = new Configuration();
			try {
				PropertiesConfiguration globalProperties = new PropertiesConfiguration();
                globalProperties.setEncoding("UTF-8");
                globalProperties.load("global.properties");
				config.setEnv(globalProperties.getString("env"));
                config.setVersion(globalProperties.getString("version"));
				PropertiesConfiguration setupProperties = new PropertiesConfiguration();
                setProperty(setupProperties);
			} catch (ConfigurationException e) {
				logger.error("加载配置文件失败:  " + e.getMessage() );
				e.printStackTrace();
			}
		}
		return config;
	}

/**
 * 加载公共元素
 * @param prop
 */
	private static void setProperty(PropertiesConfiguration prop){
		try {
			prop.setEncoding("UTF-8");
			prop.load("dynamic.properties");
			config.setDefault_photo_url(prop.getString("default_photo_url"));
			config.setDefaultPageSize(prop.getInt("default_page_size", 20));
			config.setPhotoUrlPrefix(prop.getString("photo_url_prefix"));
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

    /**
     * 设置 关联房源价格浮动区间
     * @return
     */
    public double getPriceFrom() {
        return priceFrom;
    }

    /**
     *获取 关联房源价格浮动区间
     * @param priceFrom
     */
    public void setPriceFrom(double priceFrom) {
        this.priceFrom = priceFrom;
    }

    /**
     * 设置 关联房源价格浮动区间
     * @return
     */
    public double getPriceTo() {
        return priceTo;
    }

    /**
     *获取 关联房源价格浮动区间
     * @param priceTo
     */
    public void setPriceTo(double priceTo) {
        this.priceTo = priceTo;
    }

    /**
     *设置 关联房源展示条数
     * @return
     */
    public int getUnionFySize() {
        return unionFySize;
    }

    /**
     * 获取 关联房源展示条数
     * @param unionFySize
     */
    public void setUnionFySize(int unionFySize) {
        this.unionFySize = unionFySize;
    }

    /**
	 * 获得 当前环境，测试: development 生产： production
	 * @return
	 */
	public String getEnv() {
		return env;
	}

	/**
	 * 设置 当前环境，测试: development 生产： production
	 * @param env
	 */
	public void setEnv(String env) {
		this.env = env;
	}

    /**
     * 获取 版本号
     * @return 版本号
     */
    public String getVersion() {
        return version;
    }

    /**
     * 设置 版本号
     * @param version 版本号
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
	 * 获得 列表页默认的照片url
	 * @return
	 */
	public String getDefault_photo_url() {
		return default_photo_url;
	}

	/**
	 * 设置 列表页默认的照片url
	 * @param default_photo_url
	 */
	public void setDefault_photo_url(String default_photo_url) {
		this.default_photo_url = default_photo_url;
	}

	/**
	 * 获得 二手房 面积筛选 最大值
	 * @return
	 */
	public int getSale_acreage_max() {
		return sale_acreage_max;
	}

	/**
	 * 设置 二手房 面积筛选 最大值
	 * @param sale_acreage_max
	 */
	public void setSale_acreage_max(int sale_acreage_max) {
		this.sale_acreage_max = sale_acreage_max;
	}

	/**
	 * 获得 二手房 面积筛选 最小值
	 * @return
	 */
	public int getSale_acreage_min() {
		return sale_acreage_min;
	}

	/**
	 * 设置 二手房 面积筛选 最小值
	 * @param sale_acreage_min
	 */
	public void setSale_acreage_min(int sale_acreage_min) {
		this.sale_acreage_min = sale_acreage_min;
	}

	/**
	 * 获得 二手房 价格筛选 最大值
	 * @return
	 */
	public int getSale_price_max() {
		return sale_price_max;
	}

	/**
	 * 设置 二手房 价格筛选 最大值
	 * @param sale_price_max
	 */
	public void setSale_price_max(int sale_price_max) {
		this.sale_price_max = sale_price_max;
	}

	/**
	 * 获得  二手房 价格筛选 最小值
	 * @return
	 */
	public int getSale_price_min() {
		return sale_price_min;
	}

	/**
	 * 设置  二手房 价格筛选 最小值
	 * @param sale_price_min
	 */
	public void setSale_price_min(int sale_price_min) {
		this.sale_price_min = sale_price_min;
	}

    /**
     * 获取 照片访问路径的url前缀
     * @return 照片访问路径的url前缀
     */
    public String getPhotoUrlPrefix() {
        return photoUrlPrefix;
    }

    /**
     * 设置 照片访问路径的url前缀
     * @param photoUrlPrefix 照片访问路径的url前缀
     */
    public void setPhotoUrlPrefix(String photoUrlPrefix) {
        this.photoUrlPrefix = photoUrlPrefix;
    }

    /**
     * 获取 照片访问路径的url前缀
     * @return 照片访问路径的url前缀
     */
    public String getEmployee_photo_url() {
        return employee_photo_url;
    }

    /**
     * 设置 照片访问路径的url前缀
     * @param employee_photo_url 照片访问路径的url前缀
     */
    public void setEmployee_photo_url(String employee_photo_url) {
        this.employee_photo_url = employee_photo_url;
    }

    /**
     * 获取 默认没有显示的条数
     * @return 默认没有显示的条数
     */
    public int getDefaultPageSize() {
        return defaultPageSize;
    }

    /**
     * 设置 默认没有显示的条数
     * @param defaultPageSize 默认没有显示的条数
     */
    public void setDefaultPageSize(int defaultPageSize) {
        this.defaultPageSize = defaultPageSize;
    }

    /**
     * 获取  人事招聘 主管工号，逗号隔开
     * @return  人事招聘 主管工号，逗号隔开
     */
    public String getReceivers() {
        return receivers;
    }

    /**
     * 设置  人事招聘 主管工号，逗号隔开
     * @param receivers  人事招聘 主管工号，逗号隔开
     */
    public void setReceivers(String receivers) {
        this.receivers = receivers;
    }

    /**
     * 获取 发送邮件的url
     * @return 发送邮件的url
     */
    public String getSend_email_url() {
        return send_email_url;
    }

    /**
     * 设置 发送邮件的url
     * @param send_email_url 发送邮件的url
     */
    public void setSend_email_url(String send_email_url) {
        this.send_email_url = send_email_url;
    }

    /**
     * 获取 发送邮件的内容
     * @return 发送邮件的内容
     */
    public String getEmail_content() {
        return email_content;
    }

    /**
     * 设置 发送邮件的内容
     * @param email_content  发送邮件的内容
     */
    public void setEmail_content(String email_content) {
        this.email_content = email_content;
    }

    /**
     * 获取 pc 版域名
     * @return pc 版域名
     */
    public String getWeb_domain() {
        return web_domain;
    }

    /**
     * 设置 pc 版域名
     * @param web_domain pc 版域名
     */
    public void setWeb_domain(String web_domain) {
        this.web_domain = web_domain;
    }

    /**
     * 获取 手机 版域名
     * @return 手机 版域名
     */
    public String getWap_domain() {
        return wap_domain;
    }

    /**
     * 设置 手机 版域名
     * @param wap_domain 手机 版域名
     */
    public void setWap_domain(String wap_domain) {
        this.wap_domain = wap_domain;
    }

	/**
	 * 获取分机号的url
	 * @return 获取分机号的url
	 */
	public String getFenji_url() {
		return fenji_url;
	}

	/**
	 * 设置 分机号的url
	 * @param fenji_url 分机号的url
	 */
	public void setFenji_url(String fenji_url) {
		this.fenji_url = fenji_url;
	}

    /**
     * 设置 转接号的初始值
     * @return
     */
    public int getStartExtId() {
        return startExtId;
    }

    /**
     * 获取 转接号的初始值
     * @param startExtId
     */
    public void setStartExtId(int startExtId) {
        this.startExtId = startExtId;
    }

    /**
     * 获取 转接号的最大值
     * @return
     */
    public int getMaxExtId() {
        return maxExtId;
    }

    /**
     * 设置 转接号的最大值
     * @param maxExtId
     */
    public void setMaxExtId(int maxExtId) {
        this.maxExtId = maxExtId;
    }

	/**
	 *获取 邮件抄送人
	 * @return 邮件抄送人
	 */
	public String getCopiesTo() {
		return copiesTo;
	}

	/**
	 * 设置 邮件抄送人
	 * @param copiesTo 邮件抄送人
	 */
	public void setCopiesTo(String copiesTo) {
		this.copiesTo = copiesTo;
	}

/**
     *获取 转接号临界值
     * @return
     */
    public int getCriticalValue() {
        return criticalValue;
    }

    /**
     * 设置 转接号临界值
     * @param criticalValue
     */
    public void setCriticalValue(int criticalValue) {
        this.criticalValue = criticalValue;
    }

/**
     *
     * @return
     */
    public boolean getProduction(){
        return this.getEnv().equals("production");
    }

    public boolean getIntegration(){
        return this.getEnv().equals("integration");
    }

    public boolean getDevelopment(){
        return this.getEnv().equals("development");
    }

	@Override
	public String toString() {
		return "Configuration [env=" + env + ", default_photo_url="
				+ default_photo_url + ", sale_acreage_max=" + sale_acreage_max
				+ ", sale_acreage_min=" + sale_acreage_min
				+ ", sale_price_max=" + sale_price_max + ", sale_price_min="
				+ sale_price_min + "]";
	}
	
}
