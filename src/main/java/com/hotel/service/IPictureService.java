package com.hotel.service;

import com.hotel.model.BranchPicture;
import com.hotel.model.MainPicture;
import com.hotel.model.RoomTypePicture;

import java.util.List;

/**
 * Created by apple on 15/3/27.
 */
public interface IPictureService {

    /**
     * 根据分店Id，获取对应图片的Id的List
     * @param branchId
     * @return
     * @throws Exception 
     */
    public List<Integer> getBranchPictureIds(int branchId) throws Exception;


    /**
     * 根据分店Id和图片的序号（目前为每个分店对应两张图片，序号为1，2）显示分店的图片
     * @param branchPictureId   分店Id
     * @return
     */
    public BranchPicture showBranchPicture(int branchPictureId) throws Exception;


    /**
     * 根据房间类别Id，获取对应图片的Id的List
     * @param roomTypeId
     * @return
     */
    public List<Integer> getRoomTypePictureIds(int roomTypeId) throws Exception;

    /**
     * 根据房间类别的Id和图片的序号（目前为每个房间类别对应两张图片，序号为1，2）显示房间的图片
     * @param roomTypePictureId    房间类别Id
     * @return
     */
    public RoomTypePicture showRoomTypePicture(int roomTypePictureId) throws Exception; 

    /**
     * 获取主页图片的Id的List
     * @return
     */
    public List<Integer> getMainPictureIds() throws Exception;

    /**
     * 根据主页图片Id，获取图片
     * @param mainPictureId
     * @return
     */
    public MainPicture showMainPicture(int mainPictureId) throws Exception;


}
