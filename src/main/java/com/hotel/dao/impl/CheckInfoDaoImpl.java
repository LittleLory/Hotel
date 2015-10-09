package com.hotel.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hotel.dao.ICheckInfoDao;
import com.hotel.model.BranchInfo;
import com.hotel.model.CheckInfo;
import com.hotel.model.Employee;
import com.hotel.model.RoomType;
import com.hotel.util.HOTEL;
import org.springframework.stereotype.Repository;

@Repository
public class CheckInfoDaoImpl extends BaseDao implements ICheckInfoDao
{

	@Override
	public List<CheckInfo> getCheckInfoByBranchId(int branchId)
			throws Exception
	{
		List<CheckInfo> checkInfoList = null;
		String sql = "SELECT * from CHECK_INFO WHERE BRANCH_ID = " + branchId;
		this.openconnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if (rs != null)
		{
			checkInfoList = new ArrayList<CheckInfo>();
			while (rs.next())
			{
				CheckInfo checkInfo = new CheckInfo();
				Employee employee = new Employee();
				RoomType roomType = new RoomType();
				BranchInfo branchInfo = new BranchInfo();
				
				int checkInfoId = rs.getInt("CHECK_INFO_ID");
				String idNumber = rs.getString("ID_NUMBER");
				String name = rs.getString("NAME");  //房客姓名
				int roomNumber = rs.getInt("ROOM_NUMBER");
				Date checkInTime = rs.getTimestamp("CHECK_IN_TIME");
				int numberOfDay = rs.getInt("NUMBER_OF_DAY");
				double roomPrice = rs.getDouble("ROOM_PRICE");
				double totalPrice = rs.getDouble("TOTAL_PRICE");
				Date checkOutTime = rs.getTimestamp("CHECK_OUT_TIME");
				
				String account = rs.getString("ACCOUNT");  //办理入住的员工
				int roomTypeId = rs.getInt("ROOM_TYPE_ID");
				
				checkInfo.setCheckInfoId(checkInfoId);
				checkInfo.setIDNumber(idNumber);
				checkInfo.setName(name);
				checkInfo.setRoomNumber(roomNumber);
				checkInfo.setCheckInTime(checkInTime);
				checkInfo.setNumberOfDay(numberOfDay);
				checkInfo.setRoomPrice(roomPrice);
				checkInfo.setTotalPrice(totalPrice);
				checkInfo.setCheckOutTime(checkOutTime);
				
				employee.setAccount(account);
				roomType.setRoomTypeId(roomTypeId);
				branchInfo.setBranchId(branchId);
				
				checkInfo.setEmployee(employee);
				checkInfo.setRoomType(roomType);
				checkInfo.setBranchInfo(branchInfo);
				
				checkInfoList.add(checkInfo);
			}
		}
		return checkInfoList;
	}

	@Override
	public boolean checkIn(CheckInfo checkInfo) throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
	    String sql="insert into hotel.check_info (check_info_id,id_number,name,room_number,check_in_time,number_of_day,room_price,total_price,account,branch_id,check_out_time,room_type_id) "+
                    "values ((select nvl(max(check_info_id),0)+1  from "+HOTEL.config[0]+"check_info),?,?,?,?,?,?,?,?,?,?,?)";
		openconnection();
		
		PreparedStatement pstmt=conn.prepareStatement(sql);
	    pstmt.setString(1, checkInfo.getIDNumber());
	    pstmt.setString(2, checkInfo.getName());
	    pstmt.setInt(3, checkInfo.getRoomNumber());
	    pstmt.setTimestamp(4, new java.sql.Timestamp(checkInfo.getCheckInTime().getTime()));
	    pstmt.setInt(5, checkInfo.getNumberOfDay());
	    pstmt.setDouble(6, checkInfo.getRoomPrice());
	    pstmt.setDouble(7, checkInfo.getTotalPrice());
	    pstmt.setString(8, checkInfo.getEmployee().getAccount());
	    pstmt.setInt(9, checkInfo.getBranchInfo().getBranchId());
	    pstmt.setTimestamp(10, new java.sql.Timestamp(checkInfo.getCheckOutTime().getTime()));
	    pstmt.setInt(11, checkInfo.getRoomType().getRoomTypeId());
	    
	    
	    boolean isSuccessful=pstmt.executeUpdate()!=0 ? true : false;
	    pstmt.close();
	    return isSuccessful;
	}

	@Override
	public CheckInfo getCheckInfo(int roomNumber, int branch_id)
			throws Exception {
		// TODO Auto-generated method stub
	    String sql="select * from "+HOTEL.config[0]+"check_info where room_number=? and branch_id=?";
		openconnection();
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, roomNumber);
		pstmt.setInt(2, branch_id);
	    ResultSet rs=pstmt.executeQuery();
	    
	    CheckInfo checkInfo=new CheckInfo();
	    while(rs.next()){
	    	BranchInfo branchInfo=new BranchInfo();
	    	branchInfo.setBranchId(branch_id);
            checkInfo.setBranchInfo(branchInfo);
            
            checkInfo.setCheckInfoId(rs.getInt("check_info_id"));
            checkInfo.setCheckInTime(rs.getTimestamp("check_in_time"));
            checkInfo.setCheckOutTime(rs.getTimestamp("check_out_time"));
            
            System.out.println(checkInfo.getCheckInTime());
            
            Employee employee=new Employee();
            employee.setAccount(rs.getString("account"));
            checkInfo.setEmployee(employee);
	        
            checkInfo.setIDNumber(rs.getString("id_number"));
            checkInfo.setName(rs.getString("name"));
            checkInfo.setNumberOfDay(rs.getInt("number_of_day"));
            checkInfo.setRoomNumber(rs.getInt("room_number"));
            checkInfo.setRoomPrice(rs.getDouble("room_price"));
            
            RoomType roomType=new RoomType();
            roomType.setRoomTypeId(rs.getInt("room_type_id"));
            checkInfo.setRoomType(roomType);
            
            checkInfo.setTotalPrice(rs.getDouble("total_price"));
	    }
	    rs.close();
	    pstmt.close();
	    return checkInfo;
	}

}
