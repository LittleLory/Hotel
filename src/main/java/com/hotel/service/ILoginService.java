package com.hotel.service;

import com.hotel.model.Member;

/**
 * Created by apple on 15/3/27.
 */
public interface ILoginService{

    public Member check(String account, String pwd)throws Exception;
    
}
