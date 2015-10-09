package com.hotel.dao;

import java.util.List;

import com.hotel.model.RoomType;

public interface IRoomTypeDao extends IBaseDao
{
	/**
	 * 获取所有房间类型
	 */
	public List<RoomType> showRoomType() throws Exception;
}
