package com.hotel.service;

import com.hotel.POJO.ReserveConditionInfo;
import com.hotel.model.ReserveCancelInfo;
import com.hotel.model.ReserveInfo;

import java.util.List;

/**
 * Created by apple on 15/3/27.
 */
public interface IReserveService {

    /**
     * 根据分店id和房间类别id，获取对应分店中对应房间类别的信息,branchId,roomTypeId,ROOM_PRICE,TYPE_NAME
     * @param branchId
     * @param roomTypeId
     * @return
     */
//    public BranchRoomTypeInfo showRoomInfo(int branchId, int roomTypeId);


    /**
     * 根据输入的订阅信息，创建订阅
     * @param reserveInfo
     * @return
     */
    public boolean reserve(ReserveInfo reserveInfo) throws Exception;


    /**
     * 根据ReserveConditionInfo，查询订单信息
     * ReserveConditionInfo中，
     * memberId!=null时，查询对应会员的订单信息
     * branchId!=null时，查询对应分店的订单信息
     * memberId==null&&branchId==null时，查询所有的订单信息（超级管理员使用）
     * @param info
     * @return
     * @throws Exception
     */
    public List<ReserveInfo> showReserveInfo(ReserveConditionInfo info) throws Exception;


    /**
     * 完成订单（到店并办理入住）
     * @param reserveId
     * @return
     * @throws Exception
     */
    public boolean finishReserve(int reserveId) throws Exception;


    /**
     * 取消订单（1.会员网上取消，2.分店管理员取消）
     * @param reserveId
     * @return
     * @throws Exception
     */
    public boolean cancelReserve(ReserveCancelInfo reserveCancelInfo) throws Exception;





}
