package com.dyrent.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("housePicture")
public class HousePicture implements Serializable {
	private static final long serialVersionUID = 7165653105058052436L;

	/*
	 * 图片主键
	 */
	private int id;
	/*
	 * 立即看ID
	 */
	private int messageId = 0;
	/*
	 * 图片URL
	 */
	private String pictureUrl;
	/*
	 * 图片备注，如客厅、卧室
	 */
	private String remark;
	/*
	 * 图片排序， 1为第一张，以此类推
	 */
	private int sequence;

	/**
	 * 获得 图片主键
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * 设置 图片主键
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 获得 立即看ID
	 * 
	 * @return
	 */
	public int getMessageId() {
		return messageId;
	}

	/**
	 * 设置 立即看ID
	 * 
	 * @param messageId
	 */
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	/**
	 * 获得 图片URL
	 * 
	 * @return
	 */
	public String getPictureUrl() {
		return pictureUrl;
	}

	/**
	 * 设置 图片URL
	 * 
	 * @param pictureUrl
	 */
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	/**
	 * 获得 图片备注，如客厅、卧室
	 * 
	 * @return
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置 图片备注，如客厅、卧室
	 * 
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获得 图片排序， 1为第一张，以此类推
	 * 
	 * @return
	 */
	public int getSequence() {
		return sequence;
	}

	/**
	 * 设置 图片排序， 1为第一张，以此类推
	 * 
	 * @param sequence
	 */
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	@Override
	public String toString() {
		return "HousePicture [id=" + id + ", messageId=" + messageId
				+ ", pictureUrl=" + pictureUrl + ", remark=" + remark
				+ ", sequence=" + sequence + "]";
	}

	/**
	 * 默认构造函数
	 */
	public HousePicture() {
	}

	/**
	 * 构造函数
	 */
	public HousePicture(int messageId, String pictureUrl, String remark,
			int sequence) {
		this.messageId = messageId;
		this.pictureUrl = pictureUrl;
		this.remark = remark;
		this.sequence = sequence;
	}
}
