package com.hotel.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hotel.dao.ICheckInHistoryInfoDao;
import com.hotel.model.BranchInfo;
import com.hotel.model.CheckInHistoryInfo;
import com.hotel.model.CheckInfo;
import com.hotel.model.Employee;
import com.hotel.model.RoomType;
import com.hotel.util.HOTEL;
import org.springframework.stereotype.Repository;

@Repository
public class CheckInHistoryInfoDaoImpl extends BaseDao implements ICheckInHistoryInfoDao{

	@Override
	public List<CheckInHistoryInfo> getCheckInHistoryByBranckId(int branchId)
			throws Exception 
	{
		List<CheckInHistoryInfo> checkHistoryInfoList = null;
		String sql = "SELECT * from CHECK_IN_HISTORY_INFO WHERE BRANCH_ID = " + branchId;
		this.openconnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if (rs != null)
		{
			checkHistoryInfoList = new ArrayList<CheckInHistoryInfo>();
			while (rs.next())
			{
				CheckInHistoryInfo checkInHistoryInfo = new CheckInHistoryInfo();
				Employee employee = new Employee();
				RoomType roomType = new RoomType();
				BranchInfo branchInfo = new BranchInfo();
				
				int historyId = rs.getInt("HISTORY_ID");
				String idNumber = rs.getString("ID_NUMBER");
				String name = rs.getString("NAME");  //房客姓名
				int roomNumber = rs.getInt("ROOM_NUMBER");
				Date checkInTime = rs.getTimestamp("CHECK_IN_TIME");
				Date realCheckOutTime = rs.getTimestamp("REAL_CHECK_OUT_TIME");
				double roomPrice = rs.getDouble("ROOM_PRICE");
				double totalPrice = rs.getDouble("TOTAL_PRICE");
				
				String account = rs.getString("ACCOUNT");  //办理入住的员工
				int roomTypeId = rs.getInt("ROOM_TYPE_ID");
				
				checkInHistoryInfo.setHistoryId(historyId);
				checkInHistoryInfo.setIDNumber(idNumber);
				checkInHistoryInfo.setName(name);
				checkInHistoryInfo.setRoomNumber(roomNumber);
				checkInHistoryInfo.setCheckInTime(checkInTime);
				checkInHistoryInfo.setRoomPrice(roomPrice);
				checkInHistoryInfo.setTotalPrice(totalPrice);
				checkInHistoryInfo.setRealCheckOutTime(realCheckOutTime);
				
				employee.setAccount(account);
				roomType.setRoomTypeId(roomTypeId);
				branchInfo.setBranchId(branchId);
				
				checkInHistoryInfo.setEmployee(employee);
				checkInHistoryInfo.setRoomType(roomType);
				checkInHistoryInfo.setBranchInfo(branchInfo);
				
				checkHistoryInfoList.add(checkInHistoryInfo);
			}
		}
		return checkHistoryInfoList;
	}

	@Override
	public boolean checkOut(CheckInfo checkInfo) throws Exception {
		// TODO Auto-generated method stub
		String sql="insert into "+HOTEL.config[0]+"check_in_history_info (history_id,id_number,name,room_number,check_in_time,real_check_out_time,total_price,account,branch_id,room_type_id,room_price)"+
		           "values ((select nvl(max(history_id),0)+1  from "+HOTEL.config[0]+"check_in_history_info),?,?,?,?,?,?,?,?,?,?)";
		openconnection();
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, checkInfo.getIDNumber());
		pstmt.setString(2, checkInfo.getName());
		pstmt.setInt(3, checkInfo.getRoomNumber());
		pstmt.setTimestamp(4, new java.sql.Timestamp(checkInfo.getCheckInTime().getTime()));
		pstmt.setTimestamp(5,new java.sql.Timestamp(new Date().getTime()));
		pstmt.setDouble(6, checkInfo.getTotalPrice());
		//应该是有两个员工账号
		pstmt.setString(7, checkInfo.getEmployee().getAccount());
		pstmt.setInt(8,checkInfo.getBranchInfo().getBranchId());
		pstmt.setInt(9,checkInfo.getRoomType().getRoomTypeId());
		pstmt.setDouble(10, checkInfo.getRoomPrice());
		
		boolean result=pstmt.executeUpdate()!=0  ? true : false;
		return result;
		
	}

	@Override
	public boolean deleteCheckInfo(int checkInfoId) throws Exception {
		// TODO Auto-generated method stub
		String sql="delete from "+HOTEL.config[0]+"check_info where check_info_id=?";
		openconnection();
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, checkInfoId);
		pstmt.executeUpdate();
		
		return false;
	}


}
