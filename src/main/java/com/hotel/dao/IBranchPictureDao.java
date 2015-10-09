package com.hotel.dao;

import java.util.List;

import com.hotel.model.BranchPicture;

public interface IBranchPictureDao extends IBaseDao{
	public List<Integer> getBranchPictureIds(int branchId) throws Exception;
	public BranchPicture getBranchPicture(int branchPictureId) throws Exception;
}
