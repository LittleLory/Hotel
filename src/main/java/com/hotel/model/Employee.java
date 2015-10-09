package com.hotel.model;

import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable
{
	private String account = null;
	private String password = null;
	private String name = null;
	private String sex = null;
	private String specificAddress = null;
	private String IDNumber = null;
	private Date workDate = null;
	private Province province = null;
	private City city = null;
	private Role role = null;
	private BranchInfo branch = null;
	public BranchInfo getBranch() {
		return branch;
	}
	public void setBranch(BranchInfo branch) {
		this.branch = branch;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSpecificAddress() {
		return specificAddress;
	}
	public void setSpecificAddress(String specificAddress) {
		this.specificAddress = specificAddress;
	}
	public String getIDNumber() {
		return IDNumber;
	}
	public void setIDNumber(String iDNumber) {
		IDNumber = iDNumber;
	}
	public Date getWorkDate() {
		return workDate;
	}
	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}
	public Province getProvince() {
		return province;
	}
	public void setProvince(Province province) {
		this.province = province;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String toString() {
		return  "account: " + this.account +
				" IDNumber: " + this.IDNumber +
				" name: "+ this.name +
				" password: "+ this.password +
				" sex: "+ this.sex +
				" specificAddrss: "+ this.specificAddress +
				" branchId: "+ this.branch.getBranchId() +
				" cityId: "+ this.city.getCityId() +
				" provinceId: "+ this.province.getProvinceId() +
				" roleId: "+ this.role.getRoleId() +
				" workDate: "+ this.workDate.toString();
	}
}
