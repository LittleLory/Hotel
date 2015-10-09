package com.hotel.service;

import com.hotel.model.CheckInfo;

import java.util.Date;

/**
 * Created by apple on 15/3/31.
 */
public interface IProcedureService {




    /**
     * 鍏ヤ綇
     * @param checkInfo
     * @return
     * @throws Exception 
     */
    public boolean checkIn(CheckInfo checkInfo) throws Exception;


    /**
     * 绂诲簵
     * @param roomNumber
     * @return
     * @throws Exception 
     */
    public boolean checkOut(int roomNumber, int branch_id, double real_pay) throws Exception;


    public double getTotalPrice(Date checkInTime, Date checkOutTime, int branchRoomTypeId) throws Exception;

}
