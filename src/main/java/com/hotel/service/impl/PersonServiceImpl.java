package com.hotel.service.impl;

import java.util.List;

import com.hotel.dao.IMemberDao;
import com.hotel.dao.impl.MemberDaoImpl;
import com.hotel.model.Member;
import com.hotel.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements IPersonService
{
    @Autowired
	private IMemberDao memberDao;
	@Override
	public boolean regist(Member member) throws Exception
	{
		// TODO Auto-generated method stub
		if(member != null)
		{
			try 
			{
				return memberDao.insertMemberInformation(member);
			} 
			catch (Exception e) {
				throw e;
			}
			finally
			{
				memberDao.closeResource();
			}
		}
		return false;
	}
	@Override
	public List<Member> showMember() {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public boolean updateMember(Member member) {
        return false;
    }

}
