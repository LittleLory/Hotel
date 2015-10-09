package com.hotel.controller;

import com.google.gson.Gson;
import com.hotel.POJO.LoginInfo;
import com.hotel.model.Member;
import com.hotel.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by apple on 15/3/27.
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private ILoginService loginService;



    @RequestMapping(value = "/login.html")
    public String login(){

        return "login";

    }


    /**
     * 根据账号和密码，检查登录是否成功
     * @param info
     */
    @RequestMapping(value = "/check.html")
    public void check(LoginInfo info,HttpServletRequest request,HttpServletResponse response){
        Member member = null;
        String result = null;

        try {
            member = loginService.check(info.getAccount(),info.getPwd());
        } catch (Exception e) {
            e.printStackTrace();
        }


        Map<String,Object> map = new HashMap<String, Object>();

        String json = "";

        if(member == null){

            try {
                map.put("result","false");
                json = new Gson().toJson(map);
                response.getWriter().write(json);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{

            HttpSession session = request.getSession();
            session.setAttribute("member",member);
            try {
                map.put("result","true");
                map.put("name",member.getName());

                json = new Gson().toJson(map);

                response.getWriter().write(json);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        System.out.println(json);

    }

    @RequestMapping(value = "/logout.html")
    public void logout(HttpServletRequest request,HttpServletResponse response){

        request.getSession().setAttribute("member",null);

        try {

            response.getWriter().write("true");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
