package com.hotel.dao;


import com.hotel.model.Member;

public interface IMemberDao extends IBaseDao
{
	/**
	 * 检查用户是否登录成功
	 */
	public Member check(String account, String pwd) throws Exception;
	/**
	 * 插入员全部信息
	 */
	public boolean insertMemberInformation(Member member) throws Exception;
}
