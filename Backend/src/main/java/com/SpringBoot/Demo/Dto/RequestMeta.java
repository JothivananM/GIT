package com.SpringBoot.Demo.Dto;

public class RequestMeta {

	private String userName;
	private int Id;
	private int roleId;
	private String employeeCode;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getRoleId() {
		return roleId;
	}

	public int setRoleId(int roleId) {
		return this.roleId = roleId;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

}
