package com.hotel.model;


public class City 
{
	private int cityId = 0;
	private String cityName = null;
	private char firstSpell = 0;
	private Province province = null;
	
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public char getFirstSpell() {
		return firstSpell;
	}
	public void setFirstSpell(char firstSpell) {
		this.firstSpell = firstSpell;
	}
	public Province getProvince() {
		return province;
	}
	public void setProvince(Province province) {
		this.province = province;
	}
	
}
