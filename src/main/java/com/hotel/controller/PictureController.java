package com.hotel.controller;

import com.hotel.model.BranchPicture;
import com.hotel.model.RoomTypePicture;
import com.hotel.service.IPictureService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by apple on 15/3/27.
 */
@Controller
@RequestMapping(value = "/picture")
public class PictureController {

    private IPictureService pictureService;


    /**
     * 异步加载
     * 通过分店图片的Id，获取图片
     * @param branchPictureId 分店图片Id
     * @param response
     */
    public void showBranchPicture(@RequestParam(value = "branchPictureId")int branchPictureId ,HttpServletResponse response){

        BranchPicture pic = null;

        try {
            pic = pictureService.showBranchPicture(branchPictureId);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    /**
     * 异步加载
     * 通过房间类别图片Id，获取对应图片
     * @param roomTypePictureId 房间类别图片Id
     * @param response
     */
    public void showRoomTypePicture(@RequestParam(value = "roomTypePictureId")int roomTypePictureId,HttpServletResponse response){

        RoomTypePicture pic = null;

        try {
            pic = pictureService.showRoomTypePicture(roomTypePictureId);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
