package com.hotel.dao;

import java.util.Date;
import java.util.List;

import com.hotel.POJO.SearchConditionInfo;
import com.hotel.model.BranchRoomTypeInfo;

public interface IBranchRoomTypeInfoDao extends IBaseDao 
{
	 //根据分店id获取分店类型列表，其结果按照价格排序
    public List<BranchRoomTypeInfo> getBranchRoomTypeInfo(int cityId) throws Exception;
    //根据branchRoomTypeId,获取BranchRoomTypeInfo
    public BranchRoomTypeInfo getBranchRoomTypeInfoByRoomTypeId(int branchRoomTypeId) 
    		throws Exception;
	/**
	 * 获取给定城市下边的所有酒店信息，其中room_number(房间数量)不包括状态为“维修”的房间
	 */
	public List<BranchRoomTypeInfo> getAllBranchInfoByCity(SearchConditionInfo info) throws Exception;
	/**
	 * 获取某一时间段下，具体某一酒店的某一房间类型的已住房间数
	 */
	public List<BranchRoomTypeInfo> roomNumberOfCheckIn(List<BranchRoomTypeInfo>
                                                                branchRoomTypeList, Date comeDate) throws Exception;
	/**
	 * 获取某一时间段下，具体某一酒店的某一房间类型的已预订房间数
	 */
	public List<BranchRoomTypeInfo> roomNumberOfReserve(List<BranchRoomTypeInfo>
                                                                branchRoomTypeList, Date comeDate, Date leaveDate) throws Exception;
	/**
	 * 更新分店房间类型表（BRANCH_ROOM_TYPE_INFO）
	 */
	 public boolean updateBranchRoomTypeInfo(int branchRoomTypeInfoId, Integer roomTypeId,
                                             Double price)throws Exception;
}
