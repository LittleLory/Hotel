package com.hotel.controller;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.hotel.POJO.SearchConditionInfo;
import com.hotel.model.*;
import com.hotel.service.IHotelSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by apple on 15/3/27.
 */
@Controller
@RequestMapping(value = "/hotelSearch")
public class SearchController {

    @Autowired
    private IHotelSearchService hotelSearchService;

    private static String json = null;

    /**
     * 跳转到主页
     * @return
     */
    @RequestMapping(value = "/main.html")
    public String mainPage(){



        return "main";

    }

    /**
     * 跳转到搜索页面
     * 获取搜索条件（城市Id，入住日期，离店日期），并传到搜索页面，复制到对应的表单中，然后根据条件异步加载搜索结果
     * @param request
     * @return
     */
    @RequestMapping(value = "/search.html")
    public ModelAndView searchPage(HttpServletRequest request){
        ModelAndView view = null;

        String cityId = WebUtils.findParameterValue(request,"cityId");
        String comeDate = WebUtils.findParameterValue(request,"comeDate");
        String leaveDate = WebUtils.findParameterValue(request,"leaveDate");

        System.out.println(cityId+comeDate+leaveDate);

        view = new ModelAndView("search");
        if(cityId!=null)
            view.addObject("cityId",cityId);
        if(comeDate!=null)
            view.addObject("comeDate",comeDate);
        if(leaveDate!=null)
            view.addObject("leaveDate", leaveDate);

        return view;
    }


    /**
     * 异步加载
     * 根据搜索条件（城市Id，入住日期，离店日期，价格区间，房间类别），获取分店列表
     * @param
     * @param response
     */
    @RequestMapping(value = "/showHotelListBySearchCondition.html")
    public void showHotelListBySearchCondition(HttpServletRequest request,HttpServletResponse response){

        System.out.println("~~~~~~~~");
        List<BranchRoomTypeInfo> list = null;
        SearchConditionInfo info = null;
        SimpleDateFormat sdf = null;
        sdf = new SimpleDateFormat("yyyy-MM-dd");


        String branchId = WebUtils.findParameterValue(request,"branchId");
        String cityId = WebUtils.findParameterValue(request,"cityId");

//        debug
//        cityId = "28";

        System.out.println("cityId-->"+cityId);
        String roomTypeId = WebUtils.findParameterValue(request,"roomTypeId");
        String comeDate = WebUtils.findParameterValue(request,"comeDate");
        String leaveDate = WebUtils.findParameterValue(request,"leaveDate");
        String typeId = WebUtils.findParameterValue(request,"typeId");
        String priceLow = WebUtils.findParameterValue(request,"priceLow");
        String priceHigh = WebUtils.findParameterValue(request,"priceHigh");

        System.out.println(roomTypeId+"~"+cityId+"~"+comeDate+"~"+leaveDate+"~"+priceLow+"~"+priceHigh);

//        Integer branchId = (Integer)request.getAttribute("branchId");
//        Integer cityId = (Integer)request.getAttribute("cityId");
//        String comeDate = (String)request.getAttribute("comeDate");
//        String leaveDate = (String)request.getAttribute("leaveDate");
//        Integer typeId = (Integer) request.getAttribute("typeId");
//        Integer priceLow = (Integer) request.getAttribute("priceLow");
//        Integer priceHigh = (Integer)request.getAttribute("priceHigh");

//        Integer branchId = info.getBranchId();
//        Integer cityId = info.getCityId();
//        Date comeDate = null;
//        Date leaveDate = null;
//        try {
//            comeDate = sdf.parse("2015-01-01");
//            leaveDate = sdf.parse("2015-02-02");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Integer typeId = (Integer)info.getTypeId();
//        Integer priceLow = (Integer)info.getPriceLow();
//        Integer priceHigh = (Integer)info.getPriceHigh();


//        Integer branchId = (Integer) map.get("branchId");
//        Integer cityId = (Integer)map.get("cityId");
//        String comeDate = (String)map.get("comeDate");
//        String leaveDate = (String)map.get("leaveDate");
//        Integer typeId = (Integer)map.get("typeId");
//        Integer priceLow = (Integer)map.get("priceLow");
//        Integer priceHigh = (Integer)map.get("priceHigh");

        info = new SearchConditionInfo();
//        sdf = new SimpleDateFormat("yyyy-MM-dd");
//
        if(branchId!=null && !"".equals(branchId))
            info.setBranchId(Integer.parseInt(branchId));
        if(cityId!=null && !"".equals(cityId))
            info.setCityId(Integer.parseInt(cityId));
        if(typeId!=null && !"".equals(typeId))
            info.setTypeId(Integer.parseInt(typeId));
        if(priceLow!=null && !"".equals(priceLow))
            info.setPriceLow(Integer.parseInt(priceLow));
        if(priceHigh!=null && !"".equals(priceHigh))
            info.setPriceHigh(Integer.parseInt(priceHigh));
        if(roomTypeId!=null&&!"".equals(roomTypeId))
            info.setRoomTypeId(Integer.parseInt(roomTypeId));
        if(comeDate!=null && !"".equals(comeDate)){
            try {
                info.setComeDate(sdf.parse(comeDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(leaveDate!=null && !"".equals(leaveDate)){
            try {
                info.setLeaveDate(sdf.parse(leaveDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }



        try {
            list = hotelSearchService.showHotelListBySearchCondition(info);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int count = list.size();

        Map<String,Object> map = new HashMap<String, Object>();

        map.put("list",list);
        map.put("count",count);

        json = new Gson().toJson(map);

        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    /**
     * 异步加载
     * 通过城市名称的首字母获取城市列表
     * @param firstLetter   城市名称的首字母
     * @param response
     */
    @RequestMapping(value = "/showCityList.html")
    public void showCityList(@RequestParam(value = "firstLetter")String firstLetter,HttpServletResponse response){

        List<City> list = null;

        System.out.println(firstLetter+"---first");
        try {
            list = hotelSearchService.showCityListByFirstLetter(firstLetter);
        } catch (Exception e) {
            e.printStackTrace();
        }

        json = new Gson().toJson(list);

        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(json);

    }


    /**
     * 显示热门城市
     */
    @RequestMapping(value = "/showHotCityList.html")
    public void showHotCityList(HttpServletResponse response){
        List<City> list = null;
        System.out.println("/showHotCityList.html");
        try {
            list = hotelSearchService.showHotCityList();
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
     * 异步加载
     * 通过城市的Id获取对应城市的酒店分店
     * @param cityId    城市Id
     * @param response
     */
    @RequestMapping(value = "/showBranchListByCity.html")
    public void showBranchListByCity(@RequestParam(value = "cityId")int cityId,HttpServletResponse response){

        List<BranchInfo> list = null;

        try {
            list = hotelSearchService.showBranchListByCity(cityId);
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
     * 异步加载
     * 通过城市的Id获取对应城市的超值酒店的分店
     * @param cityId    城市Id
     * @param response
     */
    @RequestMapping(value = "/showCheapBranchListByCity.html")
    public void showCheapBeachListByCity(@RequestParam(value = "cityId")int cityId,HttpServletResponse response){

        List<BranchRoomTypeInfo> list = null;

        try {
            list = hotelSearchService.showCheapBranchListByCity(cityId);
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
     * 异步加载
     * 获取搜索条件框中的房间类别Radio
     * @param response
     */
    @RequestMapping(value = "/showRoomType.html")
    public void showRoomType(HttpServletResponse response){

        List<RoomType> list = null;

        try {
            list = hotelSearchService.showRoomType();
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




}
