package com.dyrent.model;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.dyrent.base.model.Page;

@Alias("houseMessage")
public class HouseMessage extends Page{

	private static final long serialVersionUID = -1690199914256103051L;
	/**
	 * 立即看ID
	 */
	private int id;
	/**
	 * 房源code
	 */
	private String houseCode;
	/**
	 * 出租价格
	 */
	private double price;
	/**
	 * 出租单位
	 */
	private String unit;
	/**
	 * 联系电话
	 */
	private String mobilePhone;
	/**
	 * 联系人姓名
	 */
	private String userName;
	/**
	 * 创建时间
	 */
	private Date messageCreateTime;
	/**
	 * 房数
	 */
	private byte room;
	/**
	 * 厅数
	 */
	private byte hall;
	/**
	 * 卫数
	 */
	private byte toilet;
	/**
	 * 装修情况
	 */
	private String decoration;
	/**
	 * 朝向
	 */
	private String direction;
	/**
	 * 面积
	 */
	private Double acreage;
	/**
	 * 楼盘ID
	 */
	private String propertyId;
	/**
	 * 楼盘名称
	 */
	private String propertyName;
	/**
	 * 经度
	 */
	private Double longitude;
	/**
	 * 纬度
	 */
	private Double latitude;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 楼层
	 */
	private Integer floor;
	/**
	 * 总楼层
	 */
	private Integer totalFloor;
	/**
	 * 创建人工号
	 */
	private int createUserCode;
	/**
	 * 房源创建时间
	 */
	private Date createTime;
	/**
	 * 房源照片集合
	 */
	private List<HousePicture> housePictureList;
	/**
	 * 房源状态,status 0：无效 1：有效
	 */
	private int status;

	/**
	 * 房源广告标题
	 */
	private String title;
	/**
	 * 房源主图URL
	 */
	private String mainPictureUrl;
	/**
	 * 附件名称
	 */
	private String attachName;

	/**
	 * 获得 立即看ID
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * 设置 立即看ID
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 获得 房源code
	 */
	public String getHouseCode() {
		return houseCode;
	}

	/**
	 * 设置 房源code
	 * 
	 * @param houseCode
	 */
	public void setHouseCode(String houseCode) {
		this.houseCode = houseCode;
	}

	/**
	 * 获得 出租价格
	 * 
	 * @return
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * 设置 出租价格
	 * 
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * 获得 出租单位
	 * 
	 * @return
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * 设置 出租单位
	 * 
	 * @param unit
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * 获得 联系电话
	 * 
	 * @return
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}

	/**
	 * 设置 联系电话
	 * 
	 * @param mobilePhone
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	/**
	 * 获得 联系人姓名
	 * 
	 * @return
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 设置 联系人姓名
	 * 
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 获得 立即看创建时间
	 * 
	 * @return
	 */
	public Date getMessageCreateTime() {
		return messageCreateTime;
	}

	/**
	 * 设置 立即看创建时间
	 * 
	 * @param messageCreateTime
	 */
	public void setMessageCreateTime(Date messageCreateTime) {
		this.messageCreateTime = messageCreateTime;
	}

	/**
	 * 获得 房数
	 * 
	 * @return
	 */
	public byte getRoom() {
		return room;
	}

	/**
	 * 设置 房数
	 * 
	 * @param room
	 */
	public void setRoom(byte room) {
		this.room = room;
	}

	/**
	 * 获得 厅数
	 * 
	 * @return
	 */
	public byte getHall() {
		return hall;
	}

	/**
	 * 设置 厅数
	 * 
	 * @param tall
	 */
	public void setHall(byte hall) {
		this.hall = hall;
	}

	/**
	 * 获得 卫数
	 * 
	 * @return
	 */
	public byte getToilet() {
		return toilet;
	}

	/**
	 * 设置 卫数
	 * 
	 * @param toilet
	 */
	public void setToilet(byte toilet) {
		this.toilet = toilet;
	}

	/**
	 * 获得 装修情况
	 * 
	 * @return
	 */
	public String getDecoration() {
		return decoration;
	}

	/**
	 * 设置 装修情况
	 * 
	 * @param decoration
	 */
	public void setDecoration(String decoration) {
		this.decoration = decoration;
	}

	/**
	 * 获得 朝向
	 * 
	 * @return
	 */
	public String getDirection() {
		return direction;
	}

	/**
	 * 设置 朝向
	 * 
	 * @param direction
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}

	/**
	 * 获得 面积
	 * 
	 * @return
	 */
	public Double getAcreage() {
		return acreage;
	}

	/**
	 * 设置 面积
	 * 
	 * @param acreage
	 */
	public void setAcreage(Double acreage) {
		this.acreage = acreage;
	}

	/**
	 * 获得 楼盘ID
	 * 
	 * @return
	 */
	public String getPropertyId() {
		return propertyId;
	}

	/**
	 * 设置 楼盘ID
	 * 
	 * @param propertyId
	 */
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	/**
	 * 获得 楼盘名称
	 * 
	 * @return
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * 设置 楼盘名称
	 * 
	 * @param propertyName
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	/**
	 * 获得 经度
	 * 
	 * @return
	 */
	public Double getLongitude() {
		return longitude;
	}

	/**
	 * 设置 经度
	 * 
	 * @param longtude
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**
	 * 获得 纬度
	 * 
	 * @return
	 */
	public Double getLatitude() {
		return latitude;
	}

	/**
	 * 设置 纬度
	 * 
	 * @param latitude
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	/**
	 * 获得 地址
	 * 
	 * @return
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 设置 地址
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 获得 楼层
	 * 
	 * @return
	 */
	public Integer getFloor() {
		return floor;
	}

	/**
	 * 设置 楼层
	 * 
	 * @param floor
	 */
	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	/**
	 * 获得 总楼层
	 * 
	 * @return
	 */
	public Integer getTotalFloor() {
		return totalFloor;
	}

	/**
	 * 设置 总楼层
	 * 
	 * @param totalFloor
	 */
	public void setTotalFloor(Integer totalFloor) {
		this.totalFloor = totalFloor;
	}

	/**
	 * 获得 创建房源的员工工号
	 * 
	 * @return
	 */
	public int getCreateUserCode() {
		return createUserCode;
	}

	/**
	 * 设置 创建房源的员工工号
	 * 
	 * @param createUserCode
	 */
	public void setCreateUserCode(int createUserCode) {
		this.createUserCode = createUserCode;
	}

	/**
	 * 获得 房源创建时间
	 * 
	 * @return
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置 房源创建时间
	 * 
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获得 房源照片集合
	 */
	public List<HousePicture> getHousePictureList() {
		return housePictureList;
	}

	/**
	 * 设置 房源照片集合
	 * 
	 * @param housePictureList
	 */
	public void setHousePictureList(List<HousePicture> housePictureList) {
		this.housePictureList = housePictureList;
	}

	public String getFloorPosition() {
		if (this.floor == 0) {
			return "低楼层";
		}
		int yu = this.totalFloor % this.floor;
		int tempFloor = this.totalFloor;
		if (yu > 0) {
			tempFloor += 3 - yu;
		}
		int chu = tempFloor / 3;
		if (this.floor <= chu) {
			return "低区";
		} else if (this.floor <= chu * 2) {
			return "中区";
		} else {
			return "高区";
		}
	}

	/**
	 * 获得 status int 房源状态,status 0：无效 1：有效
	 * 
	 * @return status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * 设置 status 房源状态,status 0：无效 1：有效
	 * 
	 * @param status
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * 获得 title String 房源广告标题
	 * 
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置 title 房源广告标题
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获得 mainPictureUrl String 房源主图URL
	 * 
	 * @return mainPictureUrl
	 */
	public String getMainPictureUrl() {
		return mainPictureUrl;
	}

	/**
	 * 设置 mainPictureUrl 房源主图URL
	 * 
	 * @param mainPictureUrl
	 */
	public void setMainPictureUrl(String mainPictureUrl) {
		this.mainPictureUrl = mainPictureUrl;
	}

	/**
	 * 获得 attachName String 附件名称
	 * 
	 * @return attachName
	 */
	public String getAttachName() {
		return attachName;
	}

	/**
	 * 设置 attachName 附件名称
	 * 
	 * @param attachName
	 */
	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HouseMessage [id=" + id + ", houseCode=" + houseCode
				+ ", price=" + price + ", unit=" + unit + ", mobilePhone="
				+ mobilePhone + ", userName=" + userName
				+ ", messageCreateTime=" + messageCreateTime + ", room=" + room
				+ ", hall=" + hall + ", toilet=" + toilet + ", decoration="
				+ decoration + ", direction=" + direction + ", acreage="
				+ acreage + ", propertyId=" + propertyId + ", propertyName="
				+ propertyName + ", longitude=" + longitude + ", latitude="
				+ latitude + ", address=" + address + ", floor=" + floor
				+ ", totalFloor=" + totalFloor + ", createUserCode="
				+ createUserCode + ", createTime=" + createTime
				+ ", housePictureList=" + housePictureList + ", status="
				+ status + ", title=" + title + ", mainPictureUrl="
				+ mainPictureUrl + ", attachName=" + attachName + "]";
	}
}
