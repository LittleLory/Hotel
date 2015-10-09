package com.hotel.dao;

import java.util.List;

import com.hotel.model.BranchInfo;

public interface IBranchInfoDao extends IBaseDao{
	//根据城市id获取分店列表
	public List<BranchInfo> getBranchListByCity(int cityId) throws Exception;
	/**
	 * 根据分店ID获取分店信息
	 */
	public BranchInfo getBranchInfoByBranchId(int branchId) throws Exception;
	/**
	 * 更新分店信息
	 */
	public boolean updateBranchInfo(BranchInfo branchInfo) throws Exception;
	
}
