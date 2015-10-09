package com.hotel.dao;

import java.util.List;

import com.hotel.model.CheckInfo;

public interface ICheckInfoDao extends IBaseDao
{
	/**
	 * 根据分店id获取对应的正在入住的信息（不包括历史入住信息）
	 */
	public List<CheckInfo> getCheckInfoByBranchId(int branchId) throws Exception;
	/**
	 *  入住
	 */
	public boolean checkIn(CheckInfo checkInfo) throws Exception;
	//获取入住记录
	public CheckInfo getCheckInfo(int roomNumber, int branch_id) throws Exception;
}
