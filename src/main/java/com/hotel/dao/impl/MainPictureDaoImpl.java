package com.hotel.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hotel.dao.IMainPictureDao;
import com.hotel.model.MainPicture;
import com.hotel.util.HOTEL;
import org.springframework.stereotype.Repository;

@Repository
public class MainPictureDaoImpl extends BaseDao implements IMainPictureDao{

	@Override
	public List<Integer> getMainPictureIds() throws Exception {
		// TODO Auto-generated method stub
		String sql="select main_picture_id from "+HOTEL.config[0]+"main_picture";
		List<Integer> list=new ArrayList<Integer>();
		openconnection();
		PreparedStatement pstmt=conn.prepareStatement(sql);
	    ResultSet rs=pstmt.executeQuery();
	    while(rs.next()){
            list.add(rs.getInt("main_picture_id"));
	    }
	    rs.close();
	    pstmt.close();
	    return list;
	}

	@Override
	public MainPicture getMainPicture(int mainPictureId) throws Exception {
		// TODO Auto-generated method stub
		String sql="select * from "+HOTEL.config[0]+"main_picture where main_picture_id=?";
		MainPicture mainPicture=new MainPicture();
		openconnection();
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, mainPictureId);
	    ResultSet rs=pstmt.executeQuery();
	    while(rs.next()){
              mainPicture.setMainPictureId(rs.getInt("main_picture_id"));
              mainPicture.setPicture(rs.getBytes("picture"));
            
	    }
	    rs.close();
	    pstmt.close();
	    return mainPicture;
	}

}
