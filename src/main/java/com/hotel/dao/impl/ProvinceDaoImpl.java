package com.hotel.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hotel.dao.IProvinceDao;
import com.hotel.model.Province;
import org.springframework.stereotype.Repository;

@Repository
public class ProvinceDaoImpl extends BaseDao implements IProvinceDao 
{

	@Override
	public Province getProvinceById(int provinceId) throws Exception
	{
		// TODO Auto-generated method stub
		Province province = null;
		String sql = "select * from PROVINCE where PROVINCE_ID = ?";
		openconnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setInt(1, provinceId);
		ResultSet rs = ps.executeQuery();
		if(rs != null)
		{
			while(rs.next())
			{
				province = new Province();
				String provinceName = (String) rs.getObject("PROVINCE_NAME");
				province.setProvinceId(provinceId);
				province.setProvinceName(provinceName);
			}
		}
		return province;
	}
	
}
