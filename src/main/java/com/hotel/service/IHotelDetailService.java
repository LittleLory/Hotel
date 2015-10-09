package com.hotel.service;

import com.hotel.model.*;

import java.util.List;

/**
 * Created by apple on 15/3/27.
 */
public interface IHotelDetailService{


    /**
     * 根据分店Id，获取分店信息
     * @param beachId
     * @return
     */
    public BranchInfo showBranchInfo(int branchId)throws Exception;


    /**
     * 分店后台，修改分店基本信息
     * @param branchInfo
     * @return
     */
    public boolean updateBranchInfo(BranchInfo branchInfo) throws Exception;


    /**
     * 根据分店Id，获取对应分店的入住信息
     * @param branchId
     * @return
     */
    public List<CheckInfo> showCheckInfo(int branchId) throws Exception;


    /**
     * 根据分店Id，获取对应分店的历史入住信息
     * @param branchId
     * @return
     */
    public List<CheckInHistoryInfo> showCheckInHistoryInfo(int branchId)throws Exception;

/////////////////////////////////////////////
    /**
     * 根据分店Id和房间类别Id，显示对应所有的房间信息
     * @param branchId
     * @param roomTypeId
     * @return
     */
    public List<RoomInfo> showRoomInfoList(int branchId, int roomTypeId)throws Exception;


    /**
     * 更新房间信息
     * @param roomInfo
     * @return
     */
    public boolean updateRoomInfoList(RoomInfo roomInfo)throws Exception;


    /**
     * 添加房间
     * @param roomInfo
     * @return
     */
    public boolean addRoomInfo(RoomInfo roomInfo)throws Exception;

//有改动
    /**
     * 删除房间
     * @param branchId
     * @param roomTypeId
     * @return
     */
    public boolean delRoomInfo(int branchId, int roomTypeId, int roomNumber)throws Exception;

///////////////////////////////////////////////
    /**
     * 更新branchRoomTypeInfo
     * roomTypeId!=null : 修改roomType
     * price!=null : 修改价格
     * @param branchRoomTypeInfoId
     * @param roomTypeId
     * @param price
     * @return
     */
    public boolean updateBranchRoomTypeInfo(int branchRoomTypeInfoId, Integer roomTypeId, Double price)throws Exception;


    /**
     * 添加BranchRoomTypeInfo
     * @param branchId
     * @param roomTypeId
     * @param price
     * @return
     */
    public boolean addBranchRoomTypeInfo(int branchId, int roomTypeId, int price)throws Exception;


    /**
     * 删除branchRoomTypeInfo
     * @param branchRoomTypeInfoId
     * @return
     */
    public boolean delBranchRoomTypeInfo(int branchRoomTypeInfoId)throws Exception;



    /**
     * 根据分店Id，入住日期，离店日期，获取对应分店的房间类别信息
     * 剩余房间数>0：可预订
     * 剩余房间数=0：不可预订
     * @param branchId
     * @param comeDate
     * @param leaveDate
     * @return
     */
//    public List<BranchRoomTypeInfo> showRoomTypeInfo(int branchId,String comeDate,String leaveDate);
    


}
