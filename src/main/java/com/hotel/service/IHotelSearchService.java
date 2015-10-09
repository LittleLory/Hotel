package com.hotel.service;

import com.hotel.POJO.SearchConditionInfo;
import com.hotel.model.BranchInfo;
import com.hotel.model.BranchRoomTypeInfo;
import com.hotel.model.City;
import com.hotel.model.RoomType;

import java.util.List;

/**
 * Created by apple on 15/3/27.
 */
public interface IHotelSearchService {

    /**
     * 根据搜索条件（城市Id，入住日期，离店日期，最低价格，最高价格，房间类别Id），查找符合条件的分店信息（实际为对应的房间信息）
     * @param info
     * @return
     */
    public List<BranchRoomTypeInfo> showHotelListBySearchCondition(SearchConditionInfo info)throws Exception;

    /**
     * 根据城市的首字母，查询符合条件的城市
     * @param firstLetter
     * @return
     * @throws Exception 
     */
    public List<City> showCityListByFirstLetter(String firstLetter) throws Exception;

    /**
     * 获取热门城市
     * @return
     * @throws Exception 
     */
    public List<City> showHotCityList() throws Exception;

    /**
     * 根据城市Id，获取对应城市的分店信息
     * @param cityId
     * @return
     * @throws Exception 
     */
    public List<BranchInfo> showBranchListByCity(int cityId) throws Exception;

    /**
     * 根据城市Id，获取对应城市的最便宜的分店的RoomTypeInfo
     * 需要的信息：
     * brachId，brachName，
     * @param cityId
     * @return
     * @throws Exception 
     */
    public List<BranchRoomTypeInfo> showCheapBranchListByCity(int cityId) throws Exception;


    public List<RoomType> showRoomType() throws Exception;

}
