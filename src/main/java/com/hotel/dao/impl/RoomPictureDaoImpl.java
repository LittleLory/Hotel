package com.hotel.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hotel.dao.IRoomPictureDao;
import com.hotel.model.RoomType;
import com.hotel.model.RoomTypePicture;
import com.hotel.util.HOTEL;
import org.springframework.stereotype.Repository;

@Repository
public class RoomPictureDaoImpl extends BaseDao implements IRoomPictureDao{

	@Override
	public List<Integer> getRoomTypePictureIds(int roomTypeId) throws Exception {
		// TODO Auto-generated method stub
		String sql="select picture_id from "+HOTEL.config[0]+"room_picture where room_type_id=?";
		List<Integer> list=new ArrayList<Integer>();
		openconnection();
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, roomTypeId);
	    ResultSet rs=pstmt.executeQuery();
	    while(rs.next()){
            list.add(rs.getInt("picture_id"));
	    }
	    rs.close();
	    pstmt.close();
	    return list;
	}

	@Override
	public RoomTypePicture getRoomTypePicture(int roomTypePictureId)
			throws Exception {
		// TODO Auto-generated method stub
		String sql="select * from "+HOTEL.config[0]+"room_picture where picture_id=?";
		RoomTypePicture roomTypePicture=new RoomTypePicture();
		openconnection();
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, roomTypePictureId);
	    ResultSet rs=pstmt.executeQuery();
	    while(rs.next()){
             roomTypePicture.setPrictureId(rs.getInt("picture_id"));
             
             RoomType roomType=new RoomType();
             roomType.setRoomTypeId(rs.getInt("room_type_id"));
             roomTypePicture.setRoomType(roomType);
             
             roomTypePicture.setPicture(rs.getBytes("picture"));
             
	    }
	    rs.close();
	    pstmt.close();
	    return roomTypePicture;
	}
       
}
