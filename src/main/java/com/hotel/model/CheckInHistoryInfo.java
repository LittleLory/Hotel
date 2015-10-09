package com.hotel.model;

import java.util.Date;

public class CheckInHistoryInfo 
{
	private int historyId = 0;
	//房间号
	private String IDNumber = null;
	private String name = null;
	private int roomNumber = 0;
	private Date checkInTime = null;
	private Date realCheckOutTime = null;
	private double roomPrice = 0.00;
	private double totalPrice = 0.00;
	//办理退房职工
	private Employee employee = null;
	private BranchInfo branchInfo = null;
	private RoomType roomType = null;
	public int getHistoryId() {
		return historyId;
	}
	public void setHistoryId(int historyId) {
		this.historyId = historyId;
	}
	public String getIDNumber() {
		return IDNumber;
	}
	public void setIDNumber(String iDNumber) {
		IDNumber = iDNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public Date getCheckInTime() {
		return checkInTime;
	}
	public void setCheckInTime(Date checkInTime) {
		this.checkInTime = checkInTime;
	}
	public Date getRealCheckOutTime() {
		return realCheckOutTime;
	}
	public void setRealCheckOutTime(Date realCheckOutTime) {
		this.realCheckOutTime = realCheckOutTime;
	}
	public double getRoomPrice() {
		return roomPrice;
	}
	public void setRoomPrice(double roomPrice) {
		this.roomPrice = roomPrice;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public BranchInfo getBranchInfo() {
		return branchInfo;
	}
	public void setBranchInfo(BranchInfo branchInfo) {
		this.branchInfo = branchInfo;
	}
	public RoomType getRoomType() {
		return roomType;
	}
	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}
	
}
	