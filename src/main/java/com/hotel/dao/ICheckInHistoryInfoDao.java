package com.hotel.dao;

import java.util.List;

import com.hotel.model.CheckInHistoryInfo;
import com.hotel.model.CheckInfo;

public interface ICheckInHistoryInfoDao extends IBaseDao
{
	/**
	 * 根据分店ID获取对应分店的历史入住信息
	 */
	public List<CheckInHistoryInfo> getCheckInHistoryByBranckId(int branchId)throws Exception;
	//退房
	public boolean checkOut(CheckInfo checkInfo) throws Exception;
	//删除入住记录
	public boolean deleteCheckInfo(int checkInfoId) throws Exception;
}
