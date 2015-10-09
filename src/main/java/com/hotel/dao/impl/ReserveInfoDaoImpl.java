package com.hotel.dao.impl;

import java.beans.Transient;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hotel.POJO.ReserveConditionInfo;
import com.hotel.dao.IReserveInfoDao;
import com.hotel.model.BranchInfo;
import com.hotel.model.BranchRoomTypeInfo;
import com.hotel.model.Member;
import com.hotel.model.ReserveCancelInfo;
import com.hotel.model.ReserveInfo;
import com.hotel.model.RoomType;
import org.springframework.stereotype.Repository;

@Repository
public class ReserveInfoDaoImpl extends BaseDao implements IReserveInfoDao
{

	@Override
	public boolean insertReserveInfo(ReserveInfo reserveInfo)
			throws Exception 
	{
		boolean isSuccessful = false;
		String sql = "insert into RESERVE_INFO (RESERVE_ID,BRANCH_ROOM_TYPE_ID,"
				+ "RESERVE_TIME,RESERVE_RETURN_ROOM_TIME,MEMBER_ID,ROOM_PRICE,"
				+ "RESERVE_COUNT,TOTAL_PRICE,DESCRIPTION,RESERVE_STATE,RECORD_TIME) VALUES "
				+ "((SELECT MAX(RESERVE_ID) FROM RESERVE_INFO) + 1,?,?,?,?,?,?,?,?,?,?)";  //取主键最大值加1
		openconnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		
		ps.setInt(1, reserveInfo.getBranchRoomTypeInfo().getBranchRoomTypeId());
		ps.setTimestamp(2,new java.sql.Timestamp(reserveInfo.getRecordTime().getTime()));
		ps.setTimestamp(3,new java.sql.Timestamp(reserveInfo.getReserveReturnRoomTime().getTime()));
		ps.setString(4, reserveInfo.getMember().getMemberId());
		ps.setDouble(5, reserveInfo.getRoomPrice());
		ps.setInt(6, reserveInfo.getReserveCount());
		ps.setDouble(7, reserveInfo.getTotalPrice());
		ps.setString(8, reserveInfo.getDescription());
		ps.setString(9, reserveInfo.getReserveState());
		ps.setTimestamp(10,new java.sql.Timestamp(new Date().getTime()));
		
		int result = ps.executeUpdate();
		if(result > 0)
		{
			isSuccessful = true;
		}
		
		ps.close();
		return isSuccessful;
	}

	@Override
	public List<ReserveInfo> getReserveInfoByCondition(ReserveConditionInfo info)
			throws Exception
	{
		List<ReserveInfo> reserveInfoList = null;
		String sql = "select * from RESERVE_INFO_VM where 1=1";
		if (info.getBranchId() != null)
		{
			sql += " and BRANCH_ID = " + info.getBranchId();
		}
		if (info.getMemberId() != null)
		{
			sql += " and MEMBER_ID = '" + info.getMemberId() + "'";
		}
        if (info.getReserveStatus() == "预订"){
            sql += " and RESERVE_STATE = '预订'";
        } else {
            sql += " and RESERVE_STATE != '预订'";
        }
		this.openconnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if (rs != null)
		{
			reserveInfoList = new ArrayList<ReserveInfo>();
			while (rs.next())
			{
				ReserveInfo reserveInfo = new ReserveInfo();
				int reserveId = rs.getInt("RESERVE_ID");
				int branchId = rs.getInt("BRANCH_ID");
				String memberId = rs.getString("MEMBER_ID");
				String memberName = rs.getString("Name");
				String idNumber = rs.getString("ID_NUMBER");
				String phoneNumber = rs.getString("PHONE_NUMBER");
				String typeName = rs.getString("TYPE_NAME"); //房间类型名
				int reserveCount = rs.getInt("RESERVE_COUNT");
				Date reserveTime = rs.getTimestamp("RESERVE_TIME");
				Date reserveReturnRoomTime = rs.getTimestamp("RESERVE_RETURN_ROOM_TIME");
                String reserveStatus = rs.getString("RESERVE_STATE");
				
				Member member = new Member();
				BranchInfo branchInfo = new BranchInfo();
				RoomType roomType = new RoomType();
				BranchRoomTypeInfo branchRoomTypeInfo = new BranchRoomTypeInfo();
				
				member.setMemberId(memberId);
				member.setName(memberName);
				member.setIDNumber(idNumber);
				member.setPhoneNumber(phoneNumber);
				
				branchInfo.setBranchId(branchId);
				
				roomType.setTypeName( typeName);
				
				branchRoomTypeInfo.setRoomType(roomType);
				branchRoomTypeInfo.setBranchInfo(branchInfo);

                reserveInfo.setReserveId(reserveId);
				reserveInfo.setReserveCount(reserveCount);
				reserveInfo.setReserveTime(reserveTime);
				reserveInfo.setReserveReturnRoomTime(reserveReturnRoomTime);
				
				reserveInfo.setBranchRoomTypeInfo(branchRoomTypeInfo);
				reserveInfo.setMember(member);
				reserveInfo.setReserveState(reserveStatus);
				reserveInfoList.add(reserveInfo);
			}
		}
		
		rs.close();
		ps.close();
		return reserveInfoList;
	}

	@Override
	public boolean cancelReserve(ReserveCancelInfo reserveCancelInfo)
			throws Exception
	{
		boolean isSuccessful = true;
		String reserveSql = "update RESERVE_INFO set RESERVE_STATE = '" + "取消预订" + "' where "
				+ "RESERVE_ID = " + reserveCancelInfo.getReserveInfo().getReserveId();
		String cancelSql = "INSERT into RESERVE_CANCEL_INFO "
				+ "(RESERVE_ID,CANCEL_TIME,DESCRIPTION) values (?,?,?)";
		this.openconnection();
		this.beginTransaction();
		PreparedStatement ps = this.conn.prepareStatement(cancelSql);
		ps.setInt(1, reserveCancelInfo.getReserveInfo().getReserveId());
		ps.setTimestamp(2, new java.sql.Timestamp(reserveCancelInfo.getCancelTime().getTime()));
		ps.setString(3, reserveCancelInfo.getDescription());
		
		PreparedStatement ps1 = this.conn.prepareStatement(reserveSql);
		try
		{
			ps.executeUpdate();
			ps1.executeUpdate();
		}catch (Exception e)
		{
			isSuccessful = false;
			this.rollback();
			throw e;
		}finally
		{
			this.commit();
		}
		ps.close();
		return isSuccessful;
	}

	@Override
	public boolean finishReserve(int reserveId) throws Exception 
	{
		boolean isSuccessful = true;
		String sql = "update RESERVE_INFO set RESERVE_STATE = '" + "已完成" + "' "
				+ "where RESERVE_ID = " + reserveId;
		this.openconnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		int result = ps.executeUpdate();
		if (result <= 0)
		{
			isSuccessful = false;
		}
		ps.close();
		// TODO Auto-generated method stub
		return isSuccessful;
	}

}
