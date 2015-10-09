package com.hotel.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hotel.POJO.SearchConditionInfo;
import com.hotel.dao.IBranchRoomTypeInfoDao;
import com.hotel.model.BranchInfo;
import com.hotel.model.BranchRoomTypeInfo;
import com.hotel.model.City;
import com.hotel.model.Province;
import com.hotel.model.RoomType;
import com.hotel.util.HOTEL;
import org.springframework.stereotype.Repository;

@Repository
public class BranchRoomTypeInfoDaoImpl extends BaseDao implements IBranchRoomTypeInfoDao{

	@Override
	public List<BranchRoomTypeInfo> getAllBranchInfoByCity(SearchConditionInfo info) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from QUERY_BRANCH_ROOM_TYPE_INFO where 1=1";
		if (info.getCityId() != null)
		{
			sql += " and CITY_ID = " + info.getCityId();
		}
		if (info.getBranchId() != null)
		{
			sql += " and BRANCH_ID = " + info.getBranchId();
		}
		if (info.getTypeId() != null)
		{
			sql += " and ROOM_TYPE_ID = " + info.getTypeId();
		}
		if(info.getPriceLow() != null)
		{
			sql += " and ROOM_PRICE >= " + info.getPriceLow();
		}
		if(info.getPriceHigh() != null)
		{
			sql += " and ROOM_PRICE <= " + info.getPriceHigh();
		}
		
		List<BranchRoomTypeInfo> branchRoomTypeList = new ArrayList<BranchRoomTypeInfo>();
		this.openconnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if(rs != null)
		{
			while(rs.next())
			{
				BranchRoomTypeInfo branchRoomTypeInfo = new BranchRoomTypeInfo();
				
				double roomPrice = rs.getDouble("ROOM_PRICE");
				int availableRoomNumber = rs.getInt("ROOM_NUMBER");
				int totalRoomNumber = rs.getInt("TOTAL_ROOM_NUMBER");
				
				String provinceName = rs.getString("PROVINCE_NAME");
				
				String cityName = rs.getString("CITY_NAME");
				
				int roomTypeId = rs.getInt("ROOM_TYPE_ID");
				String roomTypeName = rs.getString("TYPE_NAME");
				
				int branchId = rs.getInt("BRANCH_ID");
				String branchName = rs.getString("BRANCH_NAME");
				String address = rs.getString("ADDRESS");
				
			    Province province = new Province();
			    City city = new City();
			    BranchInfo branchInfo = new BranchInfo();
			    RoomType roomType = new RoomType();
			    
			    province.setProvinceName(provinceName);
//			    city.setCityId(info.getCityId());  //debug
			    city.setCityName(cityName);
			    branchInfo.setBranchId(branchId);
			    branchInfo.setBranchName(branchName);
			    branchInfo.setAddress(address);
			    branchInfo.setCity(city);
			    branchInfo.setProvince(province);
			    roomType.setRoomTypeId(roomTypeId);
			    roomType.setTypeName(roomTypeName);
			    
			    branchRoomTypeInfo.setRoomPrice(roomPrice);
			    branchRoomTypeInfo.setAvailableRoomNumber(availableRoomNumber);
			    branchRoomTypeInfo.setTotalRoomNumber(totalRoomNumber);
			    
			    branchRoomTypeInfo.setBranchInfo(branchInfo);
			    branchRoomTypeInfo.setRoomType(roomType);
			    
			    
			    branchRoomTypeList.add(branchRoomTypeInfo);
			}
		}
		rs.close();
		ps.close();
		return branchRoomTypeList;
	}

	@Override
	public List<BranchRoomTypeInfo> roomNumberOfCheckIn(List<BranchRoomTypeInfo> 
	branchRoomTypeList,Date comeDate)throws Exception {
		// TODO Auto-generated method stub
		List<Integer> branchIdList = null;
		List<Integer> roomTypeIdList = null;
		List<Integer> roomNumberList = null;
		//退房时间大于打算入住时间
		String sql = "select BRANCH_ID,ROOM_TYPE_ID,COUNT(CHECK_OUT_TIME) AS ROOM_NUMBER"
				+ " from QUERY_CHECK_IN where CHECK_OUT_TIME > ?" 
				+ " GROUP BY BRANCH_ID,ROOM_TYPE_ID";
		this.openconnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setTimestamp(1, new java.sql.Timestamp(comeDate.getTime()));
		ResultSet rs = ps.executeQuery();
		if(rs != null)
		{
			branchIdList = new ArrayList<Integer>();
			roomTypeIdList = new ArrayList<Integer>();
			roomNumberList = new ArrayList<Integer>();
			while(rs.next())
			{
				int branchId = rs.getInt("BRANCH_ID");
				int roomTypeId = rs.getInt("ROOM_TYPE_ID");
				int roomNumber = rs.getInt("ROOM_NUMBER");
				
				branchIdList.add(branchId);
				roomTypeIdList.add(roomTypeId);
				roomNumberList.add(roomNumber);
			}
		}
		rs.close();
		ps.close();
		for (int i = 0; i < branchIdList.size(); i++) 
		{
			int branchId = branchIdList.get(i);
			int roomTypeId = roomTypeIdList.get(i);
			int roomNumber = roomNumberList.get(i);
			for (int j = 0; j < branchRoomTypeList.size(); j++) 
			{
				BranchRoomTypeInfo branchRoomTypeInfo = branchRoomTypeList.get(j);
				int oldBranchId = branchRoomTypeInfo.getBranchInfo().getBranchId();
				int oldRoomTypeId = branchRoomTypeInfo.getRoomType().getRoomTypeId();
				int oldAvailableRoomNumber = branchRoomTypeInfo.getAvailableRoomNumber();
				
				if(branchId == oldBranchId && roomTypeId == oldRoomTypeId)
				{
					oldAvailableRoomNumber -= roomNumber;
					branchRoomTypeInfo.setAvailableRoomNumber(oldAvailableRoomNumber);
					branchRoomTypeList.set(j, branchRoomTypeInfo);
				}
			}
		}
		return branchRoomTypeList;
	}

	@Override
	public List<BranchRoomTypeInfo> roomNumberOfReserve(
			List<BranchRoomTypeInfo> branchRoomTypeList, Date comeDate,
			Date leaveDate) throws Exception
	{
		// TODO Auto-generated method stub
		List<Integer> branchIdList = null;
		List<Integer> roomTypeIdList = null;
		List<Integer> roomNumberList = null;
		String sql = "select BRANCH_ID,ROOM_TYPE_ID,SUM(RESERVE_COUNT) AS ROOM_NUMBER "
				+ "FROM QUERY_RESERVE_INFO where (RESERVE_TIME < ? AND RESERVE_RETURN_ROOM_TIME > ?)"
				+ " OR (RESERVE_TIME < ? AND RESERVE_RETURN_ROOM_TIME > ?)"
				+ " GROUP BY BRANCH_ID,ROOM_TYPE_ID";
		this.openconnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setTimestamp(1, new java.sql.Timestamp(comeDate.getTime()));
		ps.setTimestamp(2, new java.sql.Timestamp(comeDate.getTime()));
		ps.setTimestamp(3, new java.sql.Timestamp(leaveDate.getTime()));
		ps.setTimestamp(4, new java.sql.Timestamp(leaveDate.getTime()));
		ResultSet rs = ps.executeQuery();
		if(rs != null)
		{
			branchIdList = new ArrayList<Integer>();
			roomTypeIdList = new ArrayList<Integer>();
			roomNumberList = new ArrayList<Integer>();
			while(rs.next())
			{
				int branchId = rs.getInt("BRANCH_ID");
				int roomTypeId = rs.getInt("ROOM_TYPE_ID");
				int roomNumber = rs.getInt("ROOM_NUMBER");
				
				branchIdList.add(branchId);
				roomTypeIdList.add(roomTypeId);
				roomNumberList.add(roomNumber);
			}
		}
		rs.close();
		ps.close();
		for (int i = 0; i < branchIdList.size(); i++) 
		{
			int branchId = branchIdList.get(i);
			int roomTypeId = roomTypeIdList.get(i);
			int roomNumber = roomNumberList.get(i);
			for (int j = 0; j < branchRoomTypeList.size(); j++) 
			{
				BranchRoomTypeInfo branchRoomTypeInfo = branchRoomTypeList.get(j);
				int oldBranchId = branchRoomTypeInfo.getBranchInfo().getBranchId();
				int oldRoomTypeId = branchRoomTypeInfo.getRoomType().getRoomTypeId();
				int oldAvailableRoomNumber = branchRoomTypeInfo.getAvailableRoomNumber();
				
				if(branchId == oldBranchId && roomTypeId == oldRoomTypeId)
				{
					oldAvailableRoomNumber -= roomNumber;
					branchRoomTypeInfo.setAvailableRoomNumber(oldAvailableRoomNumber);
					branchRoomTypeList.set(j, branchRoomTypeInfo);
				}
			}
		}
		return branchRoomTypeList;
	}

	@Override
	public List<BranchRoomTypeInfo> getBranchRoomTypeInfo(int cityId)
			throws Exception {
		// TODO Auto-generated method stub
		String sql="select T.BRANCH_ID,T.BRANCH_ROOM_TYPE_ID,T.ROOM_PRICE,T.ROOM_TYPE_ID,B.ADDRESS,B.BRANCH_NAME,b.province_id,b.city_id from "+HOTEL.config[0]+"branch_room_type_info  t,"+HOTEL.config[0]+"branch_info  b where t.branch_id=b.branch_id and b.city_id=?  order by t.room_price";
		List<BranchRoomTypeInfo> list=new ArrayList<BranchRoomTypeInfo>();
		openconnection();
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, cityId);
	    ResultSet rs=pstmt.executeQuery();
	    while(rs.next()){
            BranchRoomTypeInfo info=new BranchRoomTypeInfo();
            
            info.setRoomPrice(rs.getDouble("room_price"));
            info.setBranchRoomTypeId(rs.getInt("branch_room_type_id"));
            
            BranchInfo branchInfo=new BranchInfo();
            branchInfo.setBranchId(rs.getInt("branch_id"));
            City city=new City();
            city.setCityId(rs.getInt("city_id"));
            branchInfo.setCity(city);
            Province province=new Province();
            province.setProvinceId(rs.getInt("province_id"));
            branchInfo.setProvince(province);
            info.setBranchInfo(branchInfo);
            
            RoomType roomType=new RoomType();
            roomType.setRoomTypeId(rs.getInt("room_type_id"));
            info.setRoomType(roomType);
            list.add(info);
	    }
	    rs.close();
	    pstmt.close();
	    return list;
	}

	@Override
	public boolean updateBranchRoomTypeInfo(int branchRoomTypeInfoId,
			Integer roomTypeId, Double price) throws Exception
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BranchRoomTypeInfo getBranchRoomTypeInfoByRoomTypeId(
			int branchRoomTypeId) throws Exception {
		// TODO Auto-generated method stub
				String sql="select * from "+HOTEL.config[0]+"branch_room_type_info where branch_room_type_id="+branchRoomTypeId;
				openconnection();
				PreparedStatement pstmt=conn.prepareStatement(sql);
				ResultSet rs=pstmt.executeQuery();
				BranchRoomTypeInfo result=new BranchRoomTypeInfo();
				while(rs.next()){
					result.setBranchRoomTypeId(rs.getInt("branch_room_type_id"));
				    
					BranchInfo branchInfo=new BranchInfo();
					branchInfo.setBranchId(rs.getInt("branch_id"));
					result.setBranchInfo(branchInfo);
					
					result.setRoomPrice(rs.getDouble("room_price"));
					
					RoomType roomType=new RoomType();
					roomType.setRoomTypeId(rs.getInt("room_type_id"));
					result.setRoomType(roomType);			
					
				}
				return result;
	}

}
