package com.hotel.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hotel.dao.IRoomTypeDao;
import com.hotel.model.RoomType;
import org.springframework.stereotype.Repository;

@Repository
public class RoomTypeImpl extends BaseDao implements IRoomTypeDao{

	@Override
	public List<RoomType> showRoomType() throws Exception
	{
		List<RoomType> roomTypeList = null;
		String sql = "select * from ROOM_TYPE";
		this.openconnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if (rs != null)
		{
			roomTypeList = new ArrayList<RoomType>();
			while (rs.next())
			{
				RoomType roomType = new RoomType();
				int roomTypeId = rs.getInt("ROOM_TYPE_ID");
				String roomTypeName = rs.getString("TYPE_NAME");
				roomType.setRoomTypeId(roomTypeId);
				roomType.setTypeName(roomTypeName);
				roomTypeList.add(roomType);
			}
		}
		rs.close();
		ps.close();
		return roomTypeList;
	}

}
