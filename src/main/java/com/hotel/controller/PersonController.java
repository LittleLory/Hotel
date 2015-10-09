package com.hotel.controller;

import com.hotel.POJO.ReserveConditionInfo;
import com.hotel.model.Member;
import com.hotel.model.ReserveCancelInfo;
import com.hotel.model.ReserveInfo;
import com.hotel.service.IPersonService;
import com.hotel.service.IReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * Created by apple on 15/3/27.
 */
@Controller
@RequestMapping(value = "/person")
@SessionAttributes(value = "member")
public class PersonController {
    @Autowired
    private IPersonService personService;
    @Autowired
    private IReserveService reserveService;

    /**
     * 跳转到个人中心页面
     * @return
     */
    @RequestMapping(value = "/personCenter.html")
    public ModelAndView personCenterPage(HttpServletRequest request){
        ModelAndView view = null;
        List<ReserveInfo> reserveInfoList = null;


        Member member = (Member) request.getSession().getAttribute("member");

        ReserveConditionInfo conditionInfo = new ReserveConditionInfo();
        conditionInfo.setMemberId(member.getMemberId());
        conditionInfo.setReserveStatus("预订");

        try {
            reserveInfoList = reserveService.showReserveInfo(conditionInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }


        List<ReserveInfo> historyList = null;

        conditionInfo.setReserveStatus(null);

        try {
            historyList = reserveService.showReserveInfo(conditionInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }


        view = new ModelAndView("personInfo");
        view.addObject("reserveInfoList",reserveInfoList);
        view.addObject("historyList",historyList);


        return view;

    }


    /**
     * 异步加载
     * 根据用户Id获取个人信息
     * @param member
     * @param response
     */
    public void showPersonInfo(@ModelAttribute(value = "member")Member member,HttpServletResponse response){



    }


    /**
     * 编辑个人信息
     *
     * @return
     */
    @RequestMapping(value = "/update.html")
    public void editMemberInfo(HttpServletRequest request,HttpServletResponse response){
        boolean result = false;

        String name = WebUtils.findParameterValue(request,"name");
        String idNumber = WebUtils.findParameterValue(request,"idNumber");
        String sex = WebUtils.findParameterValue(request,"sex");
        try {
            sex = new String(sex.getBytes("iso8859-1"),"GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Integer cityId = Integer.parseInt(WebUtils.findParameterValue(request,"city"));
        Integer provinceId = Integer.parseInt(WebUtils.findParameterValue(request,"province"));

        Member member = (Member)request.getSession().getAttribute("member");
        member.setName(name);
        member.setIDNumber(idNumber);
        member.setSex(sex);
        member.getCity().setCityId(cityId);
        member.getProvince().setProvinceId(provinceId);

        try {
            result = personService.updateMember(member);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            response.getWriter().write(result+"");
        } catch (IOException e) {
            e.printStackTrace();
        }



    }


    /**
     * 修改密码
     * 1.检查旧密码正确性
     * 2.若正确，修改密码
     * @param oldPwd
     * @param newPwd
     */
    public void editLoginInfo(@RequestParam(value = "oldPwd")String oldPwd,@RequestParam(value = "newPwd")String newPwd){



    }



    /**
     * 显示用户个人预定记录
     * @param member
     */
    public void showBookInfo(@ModelAttribute(value = "member")Member member){



    }




    public void regist(ModelMap modelMap){
        Member member = null;

        try {
            boolean tag = personService.regist(member);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @RequestMapping(value = "/cancelReserve.html")
    public void cancelReserve(@RequestParam(value="reserveId")Integer reserveId,HttpServletResponse response){
        boolean result = false;

        ReserveCancelInfo reserveCancelInfo = new ReserveCancelInfo();

        ReserveInfo reserveInfo = new ReserveInfo();
        reserveInfo.setReserveId(reserveId);

        reserveCancelInfo.setReserveInfo(reserveInfo);
        reserveCancelInfo.setCancelTime(new Date());

        try {
            result = reserveService.cancelReserve(reserveCancelInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            response.getWriter().write(result+"");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}
