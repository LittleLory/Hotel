package com.hotel.model;

import java.util.Date;

public class ReserveInfo 
{
	private int reserveId = 0;
	private BranchRoomTypeInfo branchRoomTypeInfo = null;
	//预订入住时间
	private Date reserveTime = null;
	//预订退房时间
	private Date reserveReturnRoomTime = null;
	private Member member = null;
	private double roomPrice = 0.00;
	//预订房间数
	private int reserveCount = 0;
	private double totalPrice = 0;
	private String description = null;
	private String reserveState = null;
	private Date recordTime = null;
	public int getReserveId() {
		return reserveId;
	}
	public void setReserveId(int reserveId) {
		this.reserveId = reserveId;
	}
	public BranchRoomTypeInfo getBranchRoomTypeInfo() {
		return branchRoomTypeInfo;
	}
	public void setBranchRoomTypeInfo(BranchRoomTypeInfo branchRoomTypeInfo) {
		this.branchRoomTypeInfo = branchRoomTypeInfo;
	}
	public Date getReserveTime() {
		return reserveTime;
	}
	public void setReserveTime(Date reserveTime) {
		this.reserveTime = reserveTime;
	}
	public Date getReserveReturnRoomTime() {
		return reserveReturnRoomTime;
	}
	public void setReserveReturnRoomTime(Date reserveReturnRoomTime) {
		this.reserveReturnRoomTime = reserveReturnRoomTime;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public double getRoomPrice() {
		return roomPrice;
	}
	public void setRoomPrice(double roomPrice) {
		this.roomPrice = roomPrice;
	}
	public int getReserveCount() {
		return reserveCount;
	}
	public void setReserveCount(int reserveCount) {
		this.reserveCount = reserveCount;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReserveState() {
		return reserveState;
	}
	public void setReserveState(String reserveState) {
		this.reserveState = reserveState;
	}
	public Date getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
	
	
	
}
