package com.hotel.service.impl;


import java.util.List;

import com.hotel.dao.IBranchPictureDao;
import com.hotel.dao.IMainPictureDao;
import com.hotel.dao.IRoomPictureDao;
import com.hotel.dao.impl.BranchPictureDaoImpl;
import com.hotel.dao.impl.MainPictureDaoImpl;
import com.hotel.dao.impl.RoomPictureDaoImpl;
import com.hotel.model.BranchPicture;
import com.hotel.model.MainPicture;
import com.hotel.model.RoomTypePicture;
import com.hotel.service.IPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictrueServiceImpl implements IPictureService{

    @Autowired
    IBranchPictureDao branchPictureDao=new BranchPictureDaoImpl();
    @Autowired
    IRoomPictureDao roomPictureDao=new RoomPictureDaoImpl();
    @Autowired
    IMainPictureDao mainPictureDao=new MainPictureDaoImpl();

	@Override
	public List<Integer> getBranchPictureIds(int branchId) throws Exception {
		// TODO Auto-generated method stub

	      List<Integer> result=null;
	      try{
	    	  result=branchPictureDao.getBranchPictureIds(branchId);
	      }finally{
              branchPictureDao.closeResource();
	      }
	      return result;
	      
	}

	@Override
	public BranchPicture showBranchPicture(int branchPictureId) throws Exception {
		// TODO Auto-generated method stub
      BranchPicture branchPicture=null;
      try{
    	  branchPicture = branchPictureDao.getBranchPicture(branchPictureId);
      }finally{
          branchPictureDao.closeResource();
      }
      
		return branchPicture;
	}

	@Override
	public List<Integer> getRoomTypePictureIds(int roomTypeId) throws Exception {
		// TODO Auto-generated method stub
		List<Integer> result=null;
		try {
			result=roomPictureDao.getRoomTypePictureIds(roomTypeId);
		} finally {
			// TODO: handle exception
            roomPictureDao.closeResource();
		}
		return result;
	}

	@Override
	public RoomTypePicture showRoomTypePicture(int roomTypePictureId) throws Exception {
		// TODO Auto-generated method stub
		RoomTypePicture roomTypePicture=null;
		try {
			roomTypePicture=roomPictureDao.getRoomTypePicture(roomTypePictureId);
		} finally {
			// TODO: handle exception
            roomPictureDao.closeResource();
		}
		return roomTypePicture;
	}

	@Override
	public List<Integer> getMainPictureIds() throws Exception {
		// TODO Auto-generated method stub
		List<Integer> result=null;
		try{
			result=mainPictureDao.getMainPictureIds();
		}finally{
            mainPictureDao.closeResource();
		}
		return result;
	}

	@Override
	public MainPicture showMainPicture(int mainPictureId) throws Exception {
		// TODO Auto-generated method stub
		MainPicture mainPicture=null;
		try{
			mainPicture=mainPictureDao.getMainPicture(mainPictureId);
		}finally{
            mainPictureDao.closeResource();
		}
		return mainPicture;
	}

	
}
