package com.hotel.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hotel.dao.IRoomInfoDao;
import com.hotel.model.BranchInfo;
import com.hotel.model.RoomInfo;
import com.hotel.model.RoomState;
import com.hotel.model.RoomType;
import com.hotel.util.HOTEL;
import org.springframework.stereotype.Repository;

@Repository
public class RoomInfoDaoImpl extends BaseDao implements IRoomInfoDao{

	@Override
	public List<RoomInfo> getRoomInfoList(int branchId, int roomTypeId)
			throws Exception {
		// TODO Auto-generated method stub
		String sql="select * from "+HOTEL.config[0]+"room_info where branch_id=? and room_type_id=?";
		openconnection();
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, branchId);
		pstmt.setInt(2, roomTypeId);
		
		ResultSet rs=pstmt.executeQuery();
		
		List<RoomInfo> result=new ArrayList<RoomInfo>();
		while(rs.next()){
			RoomInfo roomInfo=new RoomInfo();
			
			BranchInfo branchInfo=new BranchInfo();
			branchInfo.setBranchId(rs.getInt("branch_id"));
			roomInfo.setBranchInfo(branchInfo);
			
			roomInfo.setRoomNumber(rs.getInt("room_number"));
			
			RoomState roomState = new RoomState();
			roomState.setRoomStateId(rs.getInt("room_state"));
			roomInfo.setRoomState(roomState);
			
			RoomType roomType=new RoomType();
			roomType.setRoomTypeId(rs.getInt("room_type_id"));
			roomInfo.setRoomType(roomType);
			
			result.add(roomInfo);
		}
		rs.close();
		pstmt.close();
		return result;
	}

	@Override
	public boolean updateRoomInfo(RoomInfo roomInfo) throws Exception {
		// TODO Auto-generated method stub
		String sql="update "+HOTEL.config[0]+"room_info set room_state=? where room_number=? and room_type_id=? and branch_id=?";
		openconnection();
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, roomInfo.getRoomState().getRoomStateId());
		pstmt.setInt(2, roomInfo.getRoomNumber());
		pstmt.setInt(3, roomInfo.getRoomType().getRoomTypeId());
		pstmt.setInt(4, roomInfo.getBranchInfo().getBranchId());
		
		boolean result=pstmt.execute();
		
	//	result= pstmt.execute() !=0 ? true : false;
		pstmt.close();
		
		return result;
	}

	@Override
	public boolean addRoomInfo(RoomInfo roomInfo) throws Exception {
		// TODO Auto-generated method stub
		String sql="insert into hotel.room_info (room_number,room_type_id,branch_id,room_state) "+
                   "values (?,?,?,?)";
		openconnection();
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, roomInfo.getRoomNumber());
		pstmt.setInt(2, roomInfo.getRoomType().getRoomTypeId());
		pstmt.setInt(3, roomInfo.getBranchInfo().getBranchId());
		pstmt.setInt(4, roomInfo.getRoomState().getRoomStateId());
	    boolean result=pstmt.executeUpdate()!=0 ? true : false;
	    pstmt.close();
	    return result;
	}

	@Override
	public boolean delRoomInfo(int branchId,int roomTypeId,int roomNumber) throws Exception {
		// TODO Auto-generated method stub
	    String sql="delete from "+HOTEL.config[0]+"room_info where branch_id=? and room_type_id=? and room_number=?";
	   
		openconnection();
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, branchId);
		pstmt.setInt(2, roomTypeId);
		pstmt.setInt(3, roomNumber);
		
		boolean result= pstmt.executeUpdate()!=0 ? true : false;
		pstmt.close();
	    return result;
	}
    
	
}
