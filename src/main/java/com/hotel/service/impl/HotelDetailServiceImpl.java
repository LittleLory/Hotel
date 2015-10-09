package com.hotel.service.impl;

import java.util.List;

import com.hotel.dao.IBranchInfoDao;
import com.hotel.dao.ICheckInHistoryInfoDao;
import com.hotel.dao.ICheckInfoDao;
import com.hotel.dao.IRoomInfoDao;
import com.hotel.dao.impl.BranchInfoDaoImpl;
import com.hotel.dao.impl.CheckInHistoryInfoDaoImpl;
import com.hotel.dao.impl.CheckInfoDaoImpl;
import com.hotel.dao.impl.RoomInfoDaoImpl;
import com.hotel.model.BranchInfo;
import com.hotel.model.CheckInHistoryInfo;
import com.hotel.model.CheckInfo;
import com.hotel.model.RoomInfo;
import com.hotel.service.IHotelDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelDetailServiceImpl implements IHotelDetailService
{
	@Autowired
	private IBranchInfoDao branchInfoDao;
    @Autowired
	private ICheckInfoDao checkInfoDao;
    @Autowired
	private ICheckInHistoryInfoDao checkInHistoryInfoDao;
	
	private IRoomInfoDao roomInfoDao=new RoomInfoDaoImpl();
	@Override
	public BranchInfo showBranchInfo(int branchId) throws Exception {
		// TODO Auto-generated method stub
		BranchInfo branchInfo = null;
		try {
			branchInfo = branchInfoDao.getBranchInfoByBranchId(branchId);
		} catch (Exception e) {
			throw e;
		}
		return branchInfo;
	}
	@Override
	public boolean updateBranchInfo(BranchInfo branchInfo) throws Exception 
	{
		boolean isSuccessful = false;
		try
		{
			isSuccessful = branchInfoDao.updateBranchInfo(branchInfo);
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			branchInfoDao.closeResource();
		}
		return isSuccessful;
	}
	@Override
	public List<CheckInfo> showCheckInfo(int branchId) throws Exception
	{
		List<CheckInfo> checkInfoList = null;
		try
		{
			checkInfoList = checkInfoDao.getCheckInfoByBranchId(branchId);
		}catch (Exception e)
		{
			throw e;
		}finally
		{
			checkInfoDao.closeResource();
		}
		return checkInfoList;
	}
	@Override
	public List<CheckInHistoryInfo> showCheckInHistoryInfo(int branchId)
			throws Exception 
	{
		List<CheckInHistoryInfo> checkInHistoryList = null;
		try
		{
			checkInHistoryList = checkInHistoryInfoDao.getCheckInHistoryByBranckId(branchId);
		}catch (Exception e)
		{
			throw e;
		}finally
		{
			checkInHistoryInfoDao.closeResource();
		}
		return checkInHistoryList;
	}
	@Override
	public List<RoomInfo> showRoomInfoList(int branchId, int roomTypeId) throws Exception {
		// TODO Auto-generated method stub
		List<RoomInfo> result=null;
		try {
			result=roomInfoDao.getRoomInfoList(branchId, roomTypeId);
		} finally {
			roomInfoDao.closeResource();
		}
		return result;
	}
	@Override
	public boolean updateRoomInfoList(RoomInfo roomInfo) throws Exception {
		// TODO Auto-generated method stub
		try{
			return	roomInfoDao.updateRoomInfo(roomInfo);
		}finally{
			roomInfoDao.closeResource();
		}
	}
	@Override
	public boolean addRoomInfo(RoomInfo roomInfo) throws Exception {
		// TODO Auto-generated method stub
		try{
		   return roomInfoDao.addRoomInfo(roomInfo);
		}finally{
			roomInfoDao.closeResource();
		}
	}
	
	@Override
	public boolean delRoomInfo(int branchId, int roomTypeId,int roomNumber) throws Exception {
		// TODO Auto-generated method stub
		
		try{
			return roomInfoDao.delRoomInfo(branchId, roomTypeId, roomNumber);
		}finally{
			roomInfoDao.closeResource();
		}
	}
	@Override
	public boolean updateBranchRoomTypeInfo(int branchRoomTypeInfoId,
			Integer roomTypeId, Double price) 
	{
		boolean isSuccessful = false;
		try 
		{
			
		} catch (Exception e)
		{
			// TODO: handle exception
		} finally
		{
			
		}
		return isSuccessful;
	}
	@Override
	public boolean addBranchRoomTypeInfo(int branchId, int roomTypeId, int price) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean delBranchRoomTypeInfo(int branchRoomTypeInfoId) {
		// TODO Auto-generated method stub
		return false;
	}



}
