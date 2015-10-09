package com.hotel.dao.impl;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hotel.dao.ICityDao;
import com.hotel.model.City;
import com.hotel.model.Province;
import com.hotel.util.HOTEL;
import org.springframework.stereotype.Repository;

@Repository
public class CityDaoImpl extends BaseDao implements ICityDao{

	@Override
	public City getCityById(int cityId) throws Exception 
	{
		City city = null;
		String sql = "select * from CITY where CITY_ID = ?";
		this.openconnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setInt(1, cityId);
		ResultSet rs = ps.executeQuery();
		if(rs != null)
		{
			while (rs.next()) 
			{
				city = new City();
				String cityName = (String) rs.getObject("CITY_NAME");
				char firstSpell = ((String) rs.getObject("FIRST_SPELL")).charAt(0);
				
				city.setCityId(cityId);
				city.setCityName(cityName);
				city.setFirstSpell(firstSpell);
			}
		}
		return city;
	}

	@Override
	public List<City> getAllCities() throws Exception {
		// TODO Auto-generated method stub
String sql = "select * from "+HOTEL.config[0]+"city ";

        System.out.println(sql);

        List<City> list=new ArrayList<City>();
		openconnection();
		PreparedStatement pstmt=conn.prepareStatement(sql);
	    ResultSet rs=pstmt.executeQuery();
	    while(rs.next()){
	    	City city=new City();
	    	city.setCityId(rs.getInt("city_id"));
	    	city.setCityName(rs.getString("city_name"));
	    	city.setFirstSpell(rs.getString("first_spell").charAt(0));
	    	Province province=new Province();
	    	province.setProvinceId(rs.getInt("province_id"));
	    	city.setProvince(province);
	    	list.add(city);
	    }
	    rs.close();
	    pstmt.close();
	    return list;
	}

	@Override
	public List<City> getCityListByFirstLetter(String firstLetter)
			throws Exception {
		// TODO Auto-generated method stub
		String sql="select * from "+HOTEL.config[0]+"city where first_spell='"+firstLetter+"'";
		List<City> list=new ArrayList<City>();
		openconnection();
		PreparedStatement pstmt=conn.prepareStatement(sql);
	    ResultSet rs=pstmt.executeQuery();
	    while(rs.next()){
	    	City city=new City();
	    	city.setCityId(rs.getInt("city_id"));
	    	city.setCityName(rs.getString("city_name"));
	    	city.setFirstSpell(rs.getString("first_spell").charAt(0));
	    	Province province=new Province();
	    	province.setProvinceId(rs.getInt("province_id"));
	    	city.setProvince(province);
	    	list.add(city);
	    }
	    rs.close();
	    pstmt.close();
	    return list;
	}

}
