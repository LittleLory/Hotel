package com.hotel.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import com.hotel.POJO.SearchConditionInfo;
import com.hotel.dao.IBranchInfoDao;
import com.hotel.dao.IBranchRoomTypeInfoDao;
import com.hotel.dao.ICityDao;
import com.hotel.dao.IRoomTypeDao;
import com.hotel.dao.impl.BranchInfoDaoImpl;
import com.hotel.dao.impl.BranchRoomTypeInfoDaoImpl;
import com.hotel.dao.impl.CityDaoImpl;
import com.hotel.model.BranchInfo;
import com.hotel.model.BranchRoomTypeInfo;
import com.hotel.model.City;
import com.hotel.model.RoomType;
import com.hotel.service.IHotelSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelSearchServiceImpl implements IHotelSearchService{
	public static final int cityNumber=10;
    @Autowired
	private IBranchRoomTypeInfoDao branchRoomTypeInfoDao;
    @Autowired
    private IRoomTypeDao roomTypeDao;

	@Override
	public List<BranchRoomTypeInfo> showHotelListBySearchCondition(
			SearchConditionInfo info) throws Exception 
	{

        System.out.println("roomType->"+info.getRoomTypeId());

		List<BranchRoomTypeInfo> branchRoomTypeList = null;
		try {
			branchRoomTypeList = 
					branchRoomTypeInfoDao.getAllBranchInfoByCity(info);
			branchRoomTypeList = branchRoomTypeInfoDao.roomNumberOfReserve(branchRoomTypeList,
					info.getComeDate(), info.getLeaveDate());
			branchRoomTypeList = branchRoomTypeInfoDao.roomNumberOfCheckIn(branchRoomTypeList, 
					info.getComeDate());
		} catch (Exception e) {
			throw e;
		}finally{
			branchRoomTypeInfoDao.closeResource();
		}
		
		
		return branchRoomTypeList;
	}

	@Override
	public List<City> showCityListByFirstLetter(String firstLetter)
			throws Exception {
		// TODO Auto-generated method stub
		ICityDao dao=new CityDaoImpl();
		List<City> city=null;
		try{
		   city=dao.getCityListByFirstLetter(firstLetter);
		}finally{
		   dao.closeResource();
		}
		
		return city;
	}

	@Override
	public List<City> showHotCityList() throws Exception {
		// TODO Auto-generated method stub
		int wantNumber=cityNumber;//需要获取城市的数量
		
		ICityDao dao=new CityDaoImpl();
		List<City> allCity=null;
		try{
		   allCity=dao.getAllCities();
		}finally{
		   dao.closeResource();
		}
		
		Random random=new Random();
		HashSet<Integer> set=new HashSet<Integer>(wantNumber); 
		List<City> result=new ArrayList<City>();
		int cityNum=0; //当前已经获取了多少城市 
		while(cityNum<wantNumber){
			int whichCity=random.nextInt()%allCity.size();
			if(whichCity<=0){
				whichCity=-whichCity;
			}
			if(!set.contains(whichCity)){
				result.add(allCity.get(whichCity));
				set.add(whichCity);
				cityNum++;
			}
			
		}
		return result;
	}

	@Override
	public List<BranchInfo> showBranchListByCity(int cityId) throws Exception {
		// TODO Auto-generated method stub
		IBranchInfoDao dao=new BranchInfoDaoImpl();
		List<BranchInfo> branchInfos=null;
		try{
		   branchInfos=dao.getBranchListByCity(cityId);
		}finally{
		   dao.closeResource();
		}
		
		return branchInfos;
	}

	@Override
	public List<BranchRoomTypeInfo> showCheapBranchListByCity(int cityId)
			throws Exception {
		// TODO Auto-generated method stub
		IBranchRoomTypeInfoDao dao=new BranchRoomTypeInfoDaoImpl();
		List<BranchRoomTypeInfo> branchRoomTypeInfo=null;
		try{
		  branchRoomTypeInfo=dao.getBranchRoomTypeInfo(cityId);
		   
		}finally{
		   dao.closeResource();
		}
		
		return branchRoomTypeInfo.subList(0, branchRoomTypeInfo.size()>=10 ? 10 : branchRoomTypeInfo.size());
	}

    @Override
    public List<RoomType> showRoomType() throws Exception
    {
        List<RoomType> roomTypeList = null;
        try
        {
            roomTypeList = roomTypeDao.showRoomType();
        } catch (Exception e)
        {
            throw e;
        } finally
        {
            roomTypeDao.closeResource();
        }
        return roomTypeList;
    }


}
