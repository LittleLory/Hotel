package com.hotel.model;

import java.util.Date;

public class ReserveCancelInfo
{
	private ReserveInfo  reserveInfo = null;
	private Date cancelTime = null;
	private String description = null;
	public ReserveInfo getReserveInfo() {
		return reserveInfo;
	}
	public void setReserveInfo(ReserveInfo reserveInfo) {
		this.reserveInfo = reserveInfo;
	}
	public Date getCancelTime() {
		return cancelTime;
	}
	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
