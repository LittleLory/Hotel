package com.hotel.service;

import com.hotel.model.Member;

import java.util.List;

/**
 * Created by apple on 15/3/27.
 */
public interface IPersonService {


    /**
     * 会员注册
     * 成功返回 true
     * 失败返回false
     * @param member
     * @return
     */
    public boolean regist(Member member)throws Exception;


    /**
     * 获取会员信息
     * @return
     */
    public List<Member> showMember();

    public boolean updateMember(Member member) throws Exception;



}
