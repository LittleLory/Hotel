package com.hotel.POJO;

/**
 * Created by apple on 15/3/31.
 */
public class ReserveConditionInfo {

    private String memberId;
    private Integer branchId;
    private String reserveStatus;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getReserveStatus() {
        return reserveStatus;
    }

    public void setReserveStatus(String reserveStatus) {
        this.reserveStatus = reserveStatus;
    }
}
