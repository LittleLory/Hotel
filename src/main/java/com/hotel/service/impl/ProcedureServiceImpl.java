package com.hotel.service.impl;

import java.util.Date;

import com.hotel.dao.IBranchRoomTypeInfoDao;
import com.hotel.dao.ICheckInHistoryInfoDao;
import com.hotel.dao.ICheckInfoDao;
import com.hotel.dao.impl.BranchRoomTypeInfoDaoImpl;
import com.hotel.dao.impl.CheckInHistoryInfoDaoImpl;
import com.hotel.dao.impl.CheckInfoDaoImpl;
import com.hotel.model.BranchRoomTypeInfo;
import com.hotel.model.CheckInfo;
import com.hotel.service.IProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcedureServiceImpl implements IProcedureService{
    @Autowired
    IBranchRoomTypeInfoDao branchRoomTypeInfoDao;
    @Autowired
	ICheckInHistoryInfoDao checkInHistoryInfoDao;
    @Autowired
    ICheckInfoDao checkInfoDao;
    @Override
	public boolean checkIn(CheckInfo checkInfo) throws Exception {
		// TODO Auto-generated method stub
	       try{
	    	  return checkInfoDao.checkIn(checkInfo);
	       }finally{
	    	  checkInfoDao.closeResource();
	       }
	}

	@Override
	public boolean checkOut(int roomNumber,int branch_id,double real_pay) throws Exception {
		// TODO Auto-generated method stub
		CheckInfo checkInfo=null;
		try{
			checkInfo=checkInfoDao.getCheckInfo(roomNumber, branch_id);
			checkInfo.setTotalPrice(real_pay);	
		}finally{
			checkInfoDao.closeResource();
		}
		boolean result=false;
		try{
			checkInHistoryInfoDao.beginTransaction();
			checkInHistoryInfoDao.checkOut(checkInfo);
			result=checkInHistoryInfoDao.deleteCheckInfo(checkInfo.getCheckInfoId());
			checkInHistoryInfoDao.commit();
		}catch(Exception e){
			checkInHistoryInfoDao.rollback();
			throw e;
		}finally{
			checkInHistoryInfoDao.closeResource();
		}
		return result;
	}

	@Override
	public double getTotalPrice(Date checkInTime, Date checkOutTime,
			int branchRoomTypeId) throws Exception {
		// TODO Auto-generated method stub
	    BranchRoomTypeInfo branchRoomTypeInfo=null;
		try{
	    	  branchRoomTypeInfo=branchRoomTypeInfoDao.getBranchRoomTypeInfoByRoomTypeId(branchRoomTypeId);
	      }finally{
	    	  branchRoomTypeInfoDao.closeResource();
	      }

		
		Date tempCheckInTime=getStandardTime(checkInTime,true);
		Date tempCheckOutTime=getStandardTime(checkOutTime,false);
		
		int numberOfDays=getDistanceDays(tempCheckInTime, tempCheckOutTime);
		//System.out.println(" startTime: "+tempCheckInTime+" endTime: "+tempCheckOutTime+" result: "+numberOfDays);
		
		
		return branchRoomTypeInfo.getRoomPrice()*numberOfDays;
	}
	
	
    public Date getStandardTime(Date date,boolean isCheckIn){
             Date temp=new Date(date.getTime());
             
             temp.setHours(12);
             temp.setMinutes(0);
             temp.setSeconds(0);
             
             Date beforeDate=new Date(temp.getTime());
             beforeDate.setDate(beforeDate.getDate()-1);
             
             Date result = null ;
             if(isCheckIn){
                 result = date.before(temp) ? beforeDate : temp;
             }else{
            	 Date afterDate=new Date(temp.getTime());
            	 afterDate.setDate(afterDate.getDate()+1);
            	 result = date.before(temp) ? temp : afterDate;
             }
             return result;
          
    }
    
    public int getDistanceDays(Date startTime,Date endTime){
    	long distance=endTime.getTime()-startTime.getTime();
    	return (int)Math.ceil(distance/(24*3600*1000));
    }
}
