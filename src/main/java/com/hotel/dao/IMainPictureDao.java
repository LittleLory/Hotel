package com.hotel.dao;

import java.util.List;

import com.hotel.model.MainPicture;

public interface IMainPictureDao extends IBaseDao{
	public List<Integer> getMainPictureIds() throws Exception;
	public MainPicture getMainPicture(int mainPictureId) throws Exception;
}
