package com.dyrent.utils;

import com.dyrent.common.Configuration;
import com.dyrent.enums.PhotoSizeEnum;

/**
 * Created with IntelliJ IDEA. Author: Jerry.hu Create: Jerry.hu (14-5-9 14:28)
 * Description: To change this template use File | Settings | File Templates.
 */
public class PhotoUtil {

	public static final String JPG_SUFFIX = ".jpg";

	/**
	 * 获取 图片的全路径
	 * 
	 * @param photoSizeEnum
	 *            照片尺寸枚举
	 * @param photoUrl
	 *            照片url
	 * @return 图片的全路径
	 */
	public static String getPhotoFullPath(PhotoSizeEnum photoSizeEnum,
			String photoUrl) {
		if (photoSizeEnum == PhotoSizeEnum.ORIGINAL) {
			return Configuration.getInstance().getPhotoUrlPrefix() + photoUrl;
		} else {
			return Configuration.getInstance().getPhotoUrlPrefix() + photoUrl
					+ photoSizeEnum.toString() + getPhotoSuffix(photoUrl);
		}
	}

	/**
	 * 获取 照片的后缀
	 * 
	 * @return 照片的后缀
	 */
	private static String getPhotoSuffix(String photoUrl) {
		if (photoUrl.lastIndexOf(".") > -1) {
			return photoUrl.substring(photoUrl.lastIndexOf("."),
					photoUrl.length());
		}
		return null;
	}

	/**
	 * 获取 员工照片的全路径
	 * 
	 * @param photoSizeEnum
	 *            照片尺寸枚举
	 * @param userCode
	 *            员工工号
	 * @return 员工照片的全路径
	 */
	public static String getEmployeePhotoFullPath(PhotoSizeEnum photoSizeEnum,
			int userCode) {
		return Configuration.getInstance().getEmployee_photo_url()
				+ String.valueOf(userCode) + photoSizeEnum.toString()
				+ JPG_SUFFIX;
	}
}
