package com.hotel.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import com.hotel.dao.IMemberDao;
import com.hotel.model.City;
import com.hotel.model.Member;
import com.hotel.model.Province;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDaoImpl extends BaseDao implements IMemberDao {
	@Override
	public Member check(String account, String pwd) throws Exception {
		String sql = "select * from MEMBER where MEMBER_ID = ? and PASSWORD = ?";
		Member member = null;
		this.openconnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, account);
		ps.setString(2, pwd);
		ResultSet rs = ps.executeQuery();
		if (rs != null)
		{
			while (rs.next()) 
			{
				member = new Member();
				
				String memberId = (String) rs.getObject("MEMBER_ID");
				String password = (String) rs.getObject("PASSWORD");
				String name = (String) rs.getObject("NAME");
				String sex = (String) rs.getObject("SEX");
				String specificAddress = (String) rs.getObject("SPECFIC_ADDRESS");
				String IDNumber = (String) rs.getObject("ID_NUMBER");
				Date registerDate =  (Date) rs.getObject("REGISTER_DATE");
				int provinceId = rs.getInt("PROVINCE_ID");
			    int cityId = rs.getInt("CITY_ID");
				Province province = new Province();
				City city = new City();
				province.setProvinceId(provinceId);
				city.setCityId(cityId);
				member.setMemberId(memberId);
				member.setPassword(password);
				member.setName(name);
				member.setSex(sex);
				member.setSpecificAddress(specificAddress);
				member.setIDNumber(IDNumber);
				member.setRegisterDate(registerDate);
				member.setProvince(province);
				member.setCity(city);
			}
		}
		rs.close();
		ps.close();
		return member;
	}

	@Override
	public boolean insertMemberInformation(Member member) throws Exception
	{
		// TODO Auto-generated method stub
		boolean isSuccessful = false;
		String sql = "insert into MEMBER (MEMBER_ID,PASSWORD,NAME,SEX,"
				+ "SPECFIC_ADDRESS,ID_NUMBER,REGISTER_DATE,PROVINCE_ID,"
				+ "CITY_ID) VALUES (?,?,?,?,?,?,?,?,?)";
		openconnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, member.getMemberId());
		ps.setString(2, member.getPassword());
		ps.setString(3, member.getName());
		ps.setString(4, member.getSex());
		ps.setString(5, member.getSpecificAddress());
		ps.setString(6, member.getIDNumber());
		ps.setDate(7, new java.sql.Date(member.getRegisterDate().getTime()));
		ps.setInt(8, member.getProvince().getProvinceId());
		ps.setInt(9, member.getCity().getCityId());
		
		int result =  ps.executeUpdate();
		
		if(result > 0)
		{
			isSuccessful = true;
		}
		return isSuccessful;
	}

}
