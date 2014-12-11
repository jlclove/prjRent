package com.dyrent.common;

import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * Author: Jerry.hu
 * Create: Jerry.hu (14-5-9 12:55)
 * Description: 公用常量类
 */
public final class Contants {
    /**
     * 图片尺寸 100 X 75
     */
    public static final String SIZE_100_75 = "_100x75";
    /**
     * 图片尺寸 200 X 150
     */
    public static final String SIZE_200_150 = "_200_150";
    /**
     * 图片尺寸 500 X 375
     */
    public static final String SIZE_500_375 = "_500_375";
    /**
     * 图片尺寸 600 X 450
     */
    public static final String SIZE_600_450 = "_600_450";

    /**
     *详情页，wap查询包含房型图，web不包含
     */
     public static final int wap_sort = 6;
     public static final int web_sort = 5;

    /**
     * 区域
     */
    public static final String DISTRICT="区域";
    /**
     * 板块
     */
    public static final String PLATE="板块";

    public static final TreeMap<String,Integer> roomMap = new TreeMap<String, Integer>();


    public static TreeMap<String,Integer> getRoomMap(){
        if(roomMap.size() == 0){
            roomMap.put("1室", 1);
            roomMap.put("2室", 2);
            roomMap.put("3室", 3);
            roomMap.put("4室", 4);
            roomMap.put("5室及以上", 5);
        }
        return roomMap;
    }

    /**
     * 访客的sessionName
     */
	public static final String VISITORNAME = "visitor_name";
/**
 * wap 端vip 域名前缀
 */
	public static final String VIP_PREFIX="vip";
	public static final String WAP_RPEFIX="m";
/**
 * 官网地址
 */
	public static final String DOOIOO_WEBSITE="http://www.dooioo.com";
	
	public static String PASSWORD = "1762811971ksjqyaik";
}
