package com.hotel.service.impl;

import com.hotel.dao.ICityDao;
import com.hotel.dao.IMemberDao;
import com.hotel.dao.IProvinceDao;
import com.hotel.dao.impl.CityDaoImpl;
import com.hotel.dao.impl.MemberDaoImpl;
import com.hotel.dao.impl.ProvinceDaoImpl;
import com.hotel.model.City;
import com.hotel.model.Member;
import com.hotel.model.Province;
import com.hotel.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements ILoginService
{
    @Autowired
	private ICityDao cityDao;
    @Autowired
	private IProvinceDao provinceDao;
    @Autowired
	private IMemberDao loginDao;
	@Override
	public Member check(String account, String pwd) throws Exception
	{  		
		// TODO Auto-generated method stub
		Member member = null;
		if(account != null && pwd != null)
		{
			 try 
			 {
				 member = loginDao.check(account, pwd);
				 Province province = provinceDao.getProvinceById(member.getProvince().getProvinceId());
				 City city = cityDao.getCityById(member.getCity().getCityId());
				 member.setCity(city);
				 member.setProvince(province);

			 } 
			 catch (Exception e) 
			 {
					throw e;
			 }finally
			 {
					loginDao.closeResource();
			 }			
		}
		return member;
	}

}
