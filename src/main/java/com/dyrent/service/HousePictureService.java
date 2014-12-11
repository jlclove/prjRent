package com.dyrent.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dyrent.base.service.BaseService;
import com.dyrent.model.HousePicture;

@Service
public class HousePictureService extends BaseService<HousePicture> {

	/**
	 * 获得照片列表
	 * 
	 * @param lijikanId
	 * @return
	 */
	public List<HousePicture> getPictureListByLijikanId(int lijikanId) {
		return selectList("findPictureList", lijikanId);
	}

	/**
	 * 批量插入照片
	 * 
	 * @param list
	 * @return
	 */
	public boolean insertBatchPicture(List<HousePicture> list) {
		return this.insert("batchInsert", list);
	}

	public boolean deleteByMessageId(int messageId) {
		return this.delete("deleteByMessageId", messageId);
	}
}
