package com.hotel.dao;

import com.hotel.model.Province;

public interface IProvinceDao extends IBaseDao
{
	/**
	 *根据省份id获取省份信息
	 */
	public Province getProvinceById(int provinceId) throws Exception;
}
