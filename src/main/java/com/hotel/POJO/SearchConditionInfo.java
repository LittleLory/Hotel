package com.hotel.POJO;

import java.util.Date;

/**
 * Created by apple on 15/3/27.
 */
public class SearchConditionInfo {

    private Integer branchId;
    private Integer cityId;
    private Integer roomTypeId;
    private Date comeDate;
    private Date leaveDate;
    private Integer typeId;
    private Integer priceLow;
    private Integer priceHigh;

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Date getComeDate() {
        return comeDate;
    }

    public void setComeDate(Date comeDate) {
        this.comeDate = comeDate;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getPriceLow() {
        return priceLow;
    }

    public void setPriceLow(Integer priceLow) {
        this.priceLow = priceLow;
    }

    public Integer getPriceHigh() {
        return priceHigh;
    }

    public void setPriceHigh(Integer priceHigh) {
        this.priceHigh = priceHigh;
    }

    public Integer getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Integer roomTypeId) {
        this.roomTypeId = roomTypeId;
    }
}
