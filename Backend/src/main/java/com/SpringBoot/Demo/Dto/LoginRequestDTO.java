package com.SpringBoot.Demo.Dto;

public class LoginRequestDTO {
	
	private String userPassword;
	private String companyEmailId;
	
	
	public String getPassword() {
		return userPassword;
	}
	public void setPassword(String password) {
		this.userPassword = password;
	}
	public String getCompanyEmailId() {
		return companyEmailId;
	}
	public void setCompanyEmailId(String companyEmailId) {
		this.companyEmailId = companyEmailId;
	}
   
}
