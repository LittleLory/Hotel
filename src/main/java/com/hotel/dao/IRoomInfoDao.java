package com.hotel.dao;

import java.util.List;

import com.hotel.model.RoomInfo;

public interface IRoomInfoDao extends IBaseDao{

	public List<RoomInfo> getRoomInfoList(int branchId, int roomTypeId) throws Exception;

	public boolean updateRoomInfo(RoomInfo roomInfo) throws Exception;
	
	public boolean addRoomInfo(RoomInfo roomInfo) throws Exception;
	
	public boolean delRoomInfo(int branchId, int roomTypeId, int roomNumber) throws Exception;
}
