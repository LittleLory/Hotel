package com.hotel.model;


/**
 * Created by apple on 15/3/27.
 */
public class RoomInfo {

	private int roomNumber;
	private BranchInfo branchInfo;
	private RoomType roomType;
	private RoomState roomState;
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
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
	public RoomState getRoomState() {
		return roomState;
	}
	public void setRoomState(RoomState roomState) {
		this.roomState = roomState;
	}
	
	
	
	
}
