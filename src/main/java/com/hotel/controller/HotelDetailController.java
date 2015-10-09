package com.hotel.controller;

import com.google.gson.Gson;
import com.hotel.POJO.SearchConditionInfo;
import com.hotel.model.BranchInfo;
import com.hotel.model.BranchRoomTypeInfo;
import com.hotel.model.RoomInfo;
import com.hotel.service.IHotelDetailService;
import com.hotel.service.IHotelSearchService;
import com.hotel.service.IProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by apple on 15/3/27.
 */
@Controller
@RequestMapping(value = "/hotelDetail")
public class HotelDetailController {

    @Autowired
    private IHotelDetailService detailService;
    @Autowired
    private IHotelSearchService searchService;


    private static String json;

    /**
     * 根据分店Id，跳转到酒店分店页面，同时，传递搜索条件（入住日期，离店日期），在分店页面中异步加载符合搜索条件的房间信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/hotelDetailPage.html")
    public ModelAndView hotelDetailPage(HttpServletRequest request){


        String comeDate = WebUtils.findParameterValue(request,"comeDate");
        String leaveDate = WebUtils.findParameterValue(request,"leaveDate");
        int branchId = Integer.parseInt(WebUtils.findParameterValue(request,"branchId"));

        BranchInfo branchInfo = null;
        try {
            branchInfo = detailService.showBranchInfo(branchId);
        } catch (Exception e) {
            e.printStackTrace();
        }


        ModelAndView view = new ModelAndView("hotelDetail");
        view.addObject("comeDate",comeDate);
        view.addObject("leaveDate",leaveDate);
        view.addObject("branchInfo",branchInfo);

        return view;
    }


    /**
     * 根据搜索条件（分店Id，入住日期，离店日期），获取房间类别信息
     * @param request
     * @param response
     */
    @RequestMapping(value = "/showRoomTypeList.html")
    public void showRoomTypeList(HttpServletRequest request,HttpServletResponse response){
        List<BranchRoomTypeInfo> list = null;



        Integer branchId = Integer.parseInt(WebUtils.findParameterValue(request,"branchId"));

//        debug
        branchId = 33;

        String comeDate = WebUtils.findParameterValue(request,"comeDate");
        String leaveDate = WebUtils.findParameterValue(request,"leaveDate");

        System.out.println("showRoomTypeList-->"+branchId+comeDate+leaveDate);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        SearchConditionInfo info = new SearchConditionInfo();
        info.setBranchId(branchId);

        if(comeDate!=null&&!"".equals(comeDate)&&leaveDate!=null&&!"".equals(leaveDate)){

            try {
                info.setComeDate(sdf.parse(comeDate));
                info.setLeaveDate(sdf.parse(leaveDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }


        try {
            list = searchService.showHotelListBySearchCondition(info);
        } catch (Exception e) {
            e.printStackTrace();
        }

        json = new Gson().toJson(list);

        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 跳转到预定页面
     * 根据分店Id和房间类别Id，决定跳转后预订页面的信息
     * 专递如下数据：分店Id，房间类别Id，入住时间，离店时间
     * @param request
     * @return
     */
    @RequestMapping(value = "/reservePage.html")
    public ModelAndView reservePage(HttpServletRequest request){

        ModelAndView view  = null;
        BranchRoomTypeInfo result = null;
        BranchInfo branchInfo = null;

        Integer branchId = Integer.parseInt(WebUtils.findParameterValue(request, "branchId"));
        Integer roomTypeId = Integer.parseInt(WebUtils.findParameterValue(request, "roomTypeId"));
        String comeDate = WebUtils.findParameterValue(request,"comeDate");
        String leaveDate = WebUtils.findParameterValue(request,"leaveDate");





        SearchConditionInfo info = new SearchConditionInfo();
        info.setBranchId(branchId);
        info.setRoomTypeId(roomTypeId);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            info.setComeDate(sdf.parse(comeDate));
            info.setLeaveDate(sdf.parse(leaveDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            result = searchService.showHotelListBySearchCondition(info).get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            branchInfo = detailService.showBranchInfo(branchId);
        } catch (Exception e) {
            e.printStackTrace();
        }


        view = new ModelAndView("reserve");
        view.addObject("roomInfo",result);
        view.addObject("conditionInfo",info);
        view.addObject("comeDate",comeDate);
        view .addObject("leaveDate",leaveDate);
        view.addObject("branchInfo",branchInfo);
        return view;
    }






}
