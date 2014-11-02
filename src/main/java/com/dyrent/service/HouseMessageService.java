package com.dyrent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dyrent.base.model.Page;
import com.dyrent.base.service.BaseService;
import com.dyrent.model.HouseMessage;
import com.dyrent.model.HousePicture;

@Service
@Transactional
public class HouseMessageService extends BaseService<HouseMessage> {

	@Autowired
	private HousePictureService housePictureService;

	/**
	 * 根据ID 获得详情
	 * 
	 * @param id
	 * @return
	 */
	public HouseMessage getHouseMessageById(int id) {
		return selectOne("findById", id);
	}

	/**
	 * 根据ID 获得类似房源
	 * 
	 * @param id
	 *            当前ID
	 * @return
	 */
	public List<HouseMessage> getSimilarHouseMessageList(int id) {
		return selectList("queryForSimilars", id);
	}

	/**
	 * 插入立即看详情
	 * 
	 * @param houseMessage
	 * @return
	 */
	@Transactional
	public int insertMessage(HouseMessage houseMessage) {
		if (this.insert("insertMessage", houseMessage)) {
			for (HousePicture housePicture : houseMessage.getHousePictureList()) {
				housePicture.setMessageId(houseMessage.getId());
			}
			if (housePictureService.insertBatchPicture(houseMessage
					.getHousePictureList())) {
				return houseMessage.getId();
			} else {
				return 0;
			}
		} else {
			return 0;
		}

	}
	
	/**
	 * 获得分页结果集
	 * @param page
	 * @return
	 */
	public List<HouseMessage> getListPage(Page page){
		return this.selectByPage("getListPage", page);
	}
	
	public int getListCount(Page page){
		return this.count("queryTotalCount", page);
	}
}
