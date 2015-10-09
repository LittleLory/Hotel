package com.hotel.service.impl;

import java.util.List;

import com.hotel.POJO.ReserveConditionInfo;
import com.hotel.dao.IBranchRoomTypeInfoDao;
import com.hotel.dao.IReserveInfoDao;
import com.hotel.dao.impl.ReserveInfoDaoImpl;
import com.hotel.model.BranchRoomTypeInfo;
import com.hotel.model.ReserveCancelInfo;
import com.hotel.model.ReserveInfo;
import com.hotel.service.IReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReserveServiceImpl implements IReserveService{

    @Autowired
	private IReserveInfoDao reserveInfoDao;
    @Autowired
    private IBranchRoomTypeInfoDao branchRoomTypeInfoDao;
	@Override
	public boolean reserve(ReserveInfo reserveInfo) throws Exception {
		// TODO Auto-generated method stub
		if(reserveInfo != null)
		{
			try {
                BranchRoomTypeInfo branchRoomTypeInfo = branchRoomTypeInfoDao.getBranchRoomTypeInfoByRoomTypeId(reserveInfo.getBranchRoomTypeInfo().getRoomType().getRoomTypeId());
                reserveInfo.getBranchRoomTypeInfo().setBranchRoomTypeId(branchRoomTypeInfo.getBranchRoomTypeId());
                reserveInfo.setReserveState("预订");
				return reserveInfoDao.insertReserveInfo(reserveInfo);
			} catch (Exception e) {
				// TODO: handle exception
				throw e;
			}finally{
				reserveInfoDao.closeResource();
			}
		}
		return false;
	}

	@Override
	public List<ReserveInfo> showReserveInfo(ReserveConditionInfo info)
			throws Exception {
		// TODO Auto-generated method stub
		List<ReserveInfo> reserveInfoList = null;
		if (info != null){
			try {
				reserveInfoList = reserveInfoDao.getReserveInfoByCondition(info);
			} catch (Exception e) {
				throw e;
			}finally{
				reserveInfoDao.closeResource();
			}
		}
		return reserveInfoList;
	}

	@Override
	public boolean finishReserve(int reserveId) throws Exception 
	{
		boolean isSuccessful = false;
		try
		{
			isSuccessful = reserveInfoDao.finishReserve(reserveId);
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			reserveInfoDao.closeResource();
		}
		return isSuccessful;
	}

	@Override
	public boolean cancelReserve(ReserveCancelInfo reserveCancelInfo) throws Exception {
		// TODO Auto-generated method stub
		boolean isSuccessful = false;
		try
		{
			isSuccessful = reserveInfoDao.cancelReserve(reserveCancelInfo);
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			reserveInfoDao.closeResource();
		}
		return isSuccessful;
	}

}
