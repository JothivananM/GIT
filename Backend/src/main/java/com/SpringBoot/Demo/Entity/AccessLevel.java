package com.SpringBoot.Demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="access_level")
public class AccessLevel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="role_table_id")
    private int roleTableId;	
	
	@Column(name="role_id")
    private int roleId;

	@Column(name="url")
    private String url;

	@Column(name="type")
    private String type;

	@Column(name="is_active")
    private String isActive;
	
	public AccessLevel()
	{
		
	}

	public int getRoleTableId() {
		return roleTableId;
	}

	public void setRoleTableId(int roleTableId) {
		this.roleTableId = roleTableId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "accessLevel [roleTableId=" + roleTableId + ", roleId=" + roleId + ", url=" + url + ", type=" + type
				+ ", isActive=" + isActive + "]";
	}
	
}
