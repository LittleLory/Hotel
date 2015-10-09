package com.hotel.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hotel.dao.IBranchPictureDao;
import com.hotel.model.BranchInfo;
import com.hotel.model.BranchPicture;
import com.hotel.util.HOTEL;
import org.springframework.stereotype.Repository;

@Repository
public class BranchPictureDaoImpl extends BaseDao implements IBranchPictureDao{

	@Override
	public List<Integer> getBranchPictureIds(int branchId) throws Exception {
		// TODO Auto-generated method stub
		String sql="select picture_id from "+HOTEL.config[0]+"branch_picture where branch_id=?";
		List<Integer> list=new ArrayList<Integer>();
		openconnection();
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, branchId);
	    ResultSet rs=pstmt.executeQuery();
	    while(rs.next()){
            list.add(rs.getInt("picture_id"));
	    }
	    rs.close();
	    pstmt.close();
	    return list;
	}

	@Override
	public BranchPicture getBranchPicture(int branchPictureId) throws Exception {
		// TODO Auto-generated method stub
		String sql="select * from "+HOTEL.config[0]+"branch_picture where picture_id=?";
		BranchPicture branchPicture=new BranchPicture();
		openconnection();
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, branchPictureId);
	    ResultSet rs=pstmt.executeQuery();
	    while(rs.next()){
	    	BranchInfo branchInfo=new BranchInfo();
	    	branchInfo.setBranchId(rs.getInt("branch_id"));
            branchPicture.setBranchInfo(branchInfo);
            
            branchPicture.setPictureId(rs.getInt("picture_id"));
            
            branchPicture.setPicture(rs.getBytes("picture"));
            
	    }
	    rs.close();
	    pstmt.close();
	    return branchPicture;
	}
	
	

}
