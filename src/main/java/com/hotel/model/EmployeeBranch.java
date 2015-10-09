package com.hotel.model;

public class EmployeeBranch 
{
	private BranchInfo branchInfo = null;
	private Employee employee = null;
	public BranchInfo getBranchInfo() {
		return branchInfo;
	}
	public void setBranchInfo(BranchInfo branchInfo) {
		this.branchInfo = branchInfo;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}
