package com.SpringBoot.Demo.Dto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserDetailsDTO  {
    
	private int id;	
	
	private String name;	
	
	private String employeeCode;	
	
	private String mobileNo;			
	
	private String personalEmailId;	
	
	private String companyEmailId;	
	
	private int role_id;	
	
	private int designation_id;

	private Boolean isActive;

	@Override
	public String toString() {
		return "UserDetailsDTO [companyEmailId=" + companyEmailId + ", designation_id=" + designation_id
				+ ", employeeCode=" + employeeCode + ", id=" + id + ", isActive=" + isActive + ", mobileNo=" + mobileNo
				+ ", name=" + name + ", personalEmailId=" + personalEmailId + ", role_id=" + role_id + "]";
	}

	
}
