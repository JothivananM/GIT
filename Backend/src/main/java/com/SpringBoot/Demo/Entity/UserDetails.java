package com.SpringBoot.Demo.Entity;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="user_details")
public class UserDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="employee_table_id")
	private int id;
	
	@Column(name="employee_name")
	private String name;
	
	@Column(name="employee_code")
	private String employeeCode;
	
	@Column(name="mobile_no")
	private String mobileNo;	
		
	@Column(name="personal_email")
	private String personalEmailId;
	
	@Column(name="company_email")
	private String companyEmailId;	
	
	@Column(name="role_id")
	private int role_id;
	
	@Column(name="designation_id")
	private int designation_id;	

	@Column(name="password")
	private String userPassword;	

	@Column(name="is_active", insertable = false, updatable = false)
	private Boolean isActive;

	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="created_on",insertable = true, updatable = false)
	private Timestamp createdOn = new Timestamp(System.currentTimeMillis());

	@Column(name="modified_by")
	private String modifiedBy;

	@Column(name="modified_on",insertable = false, updatable = true)
	private Timestamp modifiedOn = new Timestamp(System.currentTimeMillis());

	
	@Override
	public String toString() {
		return "UserDetails [companyEmailId=" + companyEmailId + ", designation_id=" + designation_id
				+ ", employeeCode=" + employeeCode + ", id=" + id + ", isActive=" + isActive + ", mobileNo=" + mobileNo
				+ ", name=" + name + ", personalEmailId=" + personalEmailId + ", role_id=" + role_id + ", userPassword="
				+ userPassword + "]";
	}

	
}
