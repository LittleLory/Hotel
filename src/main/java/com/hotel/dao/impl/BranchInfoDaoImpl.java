package com.hotel.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hotel.dao.IBranchInfoDao;
import com.hotel.model.BranchInfo;
import com.hotel.model.City;
import com.hotel.model.Province;
import com.hotel.util.HOTEL;
import org.springframework.stereotype.Repository;

@Repository
public class BranchInfoDaoImpl extends BaseDao implements IBranchInfoDao{
	

	public List<BranchInfo> getBranchListByCity(int cityId) throws Exception{
		String sql="select * from "+HOTEL.config[0]+"branch_info where city_id="+cityId;
		List<BranchInfo> list=new ArrayList<BranchInfo>();
		openconnection();
		PreparedStatement pstmt=conn.prepareStatement(sql);
	    ResultSet rs=pstmt.executeQuery();
	    while(rs.next()){
	    	BranchInfo branchInfo=new BranchInfo();
            branchInfo.setAddress(rs.getString("address"));
            branchInfo.setBranchId(rs.getInt("branch_id"));
            branchInfo.setBranchName(rs.getString("branch_name"));
            City city=new City();
            city.setCityId(rs.getInt("city_id"));
            Province province=new Province();
	    	province.setProvinceId(rs.getInt("province_id"));
	    	branchInfo.setCity(city);
	    	branchInfo.setProvince(province);
	    	list.add(branchInfo);
	    }
	    rs.close();
	    pstmt.close();
	    return list;
	}

	@Override
	public BranchInfo getBranchInfoByBranchId(int branchId) throws Exception
	{
		// TODO Auto-generated method stub
		BranchInfo branchInfo = null;
		String sql = "select * from BRANCH_INFO where BRANCH_ID = ?";
		this.openconnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setInt(1, branchId);
		ResultSet rs = ps.executeQuery();
		if (rs != null)
		{
			branchInfo = new BranchInfo();
			while (rs.next())
			{
				int cityId = rs.getInt("CITY_ID");
				int provinceId = rs.getInt("PROVINCE_ID");
				String branchName = rs.getString("BRANCH_NAME");
				String address = rs.getString("ADDRESS");
				
				City city = new City();
				city.setCityId(cityId);
				Province province = new Province();
				province.setProvinceId(provinceId);
				branchInfo.setBranchId(branchId);
				branchInfo.setCity(city);
				branchInfo.setProvince(province);
				branchInfo.setBranchName(branchName);
				branchInfo.setAddress(address);
			}
		}
		rs.close();
		ps.close();
		return branchInfo;
	}

	@Override
	public boolean updateBranchInfo(BranchInfo branchInfo) throws Exception
	{
		boolean isSuccessful = true;
		String sql = "update BRANCH_INFO SET PROVINCE_ID = ?,CITY_ID = ?,"
				+ "ADDRESS = ?,BRANCH_NAME = ? where BRANCH_ID = " + branchInfo.getBranchId();
		this.openconnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setInt(1, branchInfo.getProvince().getProvinceId());
		ps.setInt(2, branchInfo.getCity().getCityId());
		ps.setString(3, branchInfo.getAddress());
		ps.setString(4, branchInfo.getBranchName());
		int result = ps.executeUpdate();
		if (result <= 0)
		{
			isSuccessful = false;
		}
		return isSuccessful;
	}
}
