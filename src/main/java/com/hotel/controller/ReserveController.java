package com.hotel.controller;

import com.google.gson.Gson;
import com.hotel.POJO.SearchConditionInfo;
import com.hotel.model.*;
import com.hotel.service.IHotelSearchService;
import com.hotel.service.IProcedureService;
import com.hotel.service.IReserveService;
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
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by apple on 15/3/27.
 */
@Controller
@RequestMapping(value = "/reserve")
public class ReserveController {

    @Autowired
    private IReserveService reserveService;

    private IHotelSearchService searchService;
    @Autowired
    private IProcedureService procedureService;


    private static String json = "";

    /**
     * 异步加载
     * 根据分店Id和类别Id，查询房间信息
     * @param comeDate
     * @param leaveDate
     * @param roomTypeId
     */
    @RequestMapping(value = "/totalPrice.html")
    public void showTotalPrice(@RequestParam(value = "comeDate")String comeDate,@RequestParam(value = "leaveDate")String leaveDate,@RequestParam(value = "roomTypeId")int roomTypeId,HttpServletResponse response){

        double totalPrice = 0;

        Date checkInDate = null;
        Date checkOutDate = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            checkInDate = sdf.parse(comeDate);
            checkOutDate = sdf.parse(leaveDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        try {
            totalPrice = procedureService.getTotalPrice(checkInDate, checkOutDate, roomTypeId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        json = new Gson().toJson(totalPrice);
        System.out.println(json+"total");
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    /**
     * 订阅
     * 根据订阅信息（branchRoomTypeId，入住时间，离店时间，订阅数量，入住人，联系方式，最晚到点时间），创建订阅
     * @param request
     * @return
     */
    @RequestMapping(value = "/reserve.html")
    public void reserve(HttpServletRequest request,HttpServletResponse response){
        ReserveInfo reserveInfo = null;
        boolean result = false;


        Integer branchId = Integer.parseInt(WebUtils.findParameterValue(request,"branchId"));
        Integer roomTypeId = Integer.parseInt(WebUtils.findParameterValue(request,"roomTypeId"));
        String comeDate = WebUtils.findParameterValue(request,"comeDate");
        String leaveDate = WebUtils.findParameterValue(request,"leaveDate");
        Integer roomCount = Integer.parseInt(WebUtils.findParameterValue(request,"roomCount"));
        Double totalPrice = Double.parseDouble(WebUtils.findParameterValue(request,"totalPrice"));
//        String name = (String)request.getAttribute("name");
//        String tel = (String)request.getAttribute("tel");
        String latestTime = WebUtils.findParameterValue(request,"latestTime");
        Member member = (Member)request.getSession().getAttribute("member");

        Date reserveTime = null;
        Date reserveReturnRoomTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            reserveTime = sdf.parse(comeDate);
            reserveReturnRoomTime = sdf.parse(leaveDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        reserveInfo = new ReserveInfo();

        BranchRoomTypeInfo branchRoomTypeInfo = new BranchRoomTypeInfo();

        BranchInfo branchInfo = new BranchInfo();
        branchInfo.setBranchId(branchId);
        branchRoomTypeInfo.setBranchInfo(branchInfo);

        RoomType roomType = new RoomType();
        roomType.setRoomTypeId(roomTypeId);
        branchRoomTypeInfo.setRoomType(roomType);

        reserveInfo.setBranchRoomTypeInfo(branchRoomTypeInfo);
        reserveInfo.setRecordTime(reserveTime);
        reserveInfo.setReserveReturnRoomTime(reserveReturnRoomTime);
        reserveInfo.setReserveCount(roomCount);
        reserveInfo.setTotalPrice(totalPrice);
        reserveInfo.setMember(member);



        try {
            result = reserveService.reserve(reserveInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            PrintWriter writer = response.getWriter();

            writer.write(result+"");

        } catch (IOException e) {
            e.printStackTrace();
        }




    }



}
