package com.hotel.dao;

import java.util.List;

import com.hotel.model.City;

public interface ICityDao extends IBaseDao 
{
	 //获取所有城市
	public List<City> getAllCities() throws Exception;
	//根据首字母获取城市列表
	public List<City> getCityListByFirstLetter(String firstLetter) throws Exception;
	/**
	 *根据城市id获取城市信息
	 */
	public City getCityById(int cityId) throws Exception ;
}
