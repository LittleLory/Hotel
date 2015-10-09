package com.hotel.model;

public class MainPicture
{
	private int mainPictureId = 0;
	private byte[] picture = null;
	public int getMainPictureId() {
		return mainPictureId;
	}
	public void setMainPictureId(int mainPictureId) {
		this.mainPictureId = mainPictureId;
	}
	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	
}
