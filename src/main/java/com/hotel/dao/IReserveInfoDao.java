package com.hotel.dao;

import java.util.List;

import com.hotel.POJO.ReserveConditionInfo;
import com.hotel.model.ReserveCancelInfo;
import com.hotel.model.ReserveInfo;

public interface IReserveInfoDao extends IBaseDao
{
	/**
	 * 生成预订房间信息
	 */
	public boolean insertReserveInfo(ReserveInfo reserveInfo) throws Exception;
	/**
	 * 根据条件查询预订信息
	 */
	public List<ReserveInfo> getReserveInfoByCondition(ReserveConditionInfo info)throws Exception;
	/**
	 * 取消预订   ----------有改动
	 */
	public boolean cancelReserve(ReserveCancelInfo reserveCancelInfo)throws Exception;
	/**
	 * 入住后将订单状态改为已完成
	 */
	public boolean finishReserve(int reserveId)throws Exception;
}
