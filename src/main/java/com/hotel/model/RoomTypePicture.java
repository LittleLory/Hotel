package com.hotel.model;


public class RoomTypePicture {
	private int prictureId;
	private RoomType roomType;
	private byte[] picture;
	public int getPrictureId() {
		return prictureId;
	}
	public void setPrictureId(int prictureId) {
		this.prictureId = prictureId;
	}
	public RoomType getRoomType() {
		return roomType;
	}
	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}
	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	
}
