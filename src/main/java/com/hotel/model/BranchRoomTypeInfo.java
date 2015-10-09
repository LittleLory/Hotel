package com.hotel.model;


public class BranchRoomTypeInfo 
{
	private int branchRoomTypeId = 0;
	private BranchInfo branchInfo = null;
	private RoomType roomType = null;
	private double roomPrice = 0.00;
	//房间总数
	private int totalRoomNumber = 0;
	//可用房间数（具体时间段）
	private int availableRoomNumber = 0;

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
	public int getBranchRoomTypeId() {
		return branchRoomTypeId;
	}
	public void setBranchRoomTypeId(int branchRoomTypeId) {
		this.branchRoomTypeId = branchRoomTypeId;
	}
	public double getRoomPrice() {
		return roomPrice;
	}
	public void setRoomPrice(double roomPrice) {
		this.roomPrice = roomPrice;
	}
	public int getTotalRoomNumber() {
		return totalRoomNumber;
	}
	public void setTotalRoomNumber(int totalRoomNumber) {
		this.totalRoomNumber = totalRoomNumber;
	}
	public int getAvailableRoomNumber() {
		return availableRoomNumber;
	}
	public void setAvailableRoomNumber(int availableRoomNumber) {
		this.availableRoomNumber = availableRoomNumber;
	}

	
}
