package com.hotel.dao;

import java.util.List;

import com.hotel.model.RoomTypePicture;

public interface IRoomPictureDao extends IBaseDao {
	public List<Integer> getRoomTypePictureIds(int roomTypeId) throws Exception;
	public RoomTypePicture getRoomTypePicture(int roomTypePictureId) throws Exception;
}
