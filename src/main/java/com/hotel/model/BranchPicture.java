package com.hotel.model;


public class BranchPicture 
{
	private int pictureId = 0;
	private BranchInfo branchInfo = null;
	private byte[] picture = null;
	public int getPictureId() {
		return pictureId;
	}
	public void setPictureId(int pictureId) {
		this.pictureId = pictureId;
	}
	public BranchInfo getBranchInfo() {
		return branchInfo;
	}
	public void setBranchInfo(BranchInfo branchInfo) {
		this.branchInfo = branchInfo;
	}
	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	
}
