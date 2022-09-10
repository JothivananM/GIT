package com.SpringBoot.Demo.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDTO {

	private int roleId;
	
	private String role;

	@Override
	public String toString() {
		return "RoleDTO [role=" + role + ", roleId=" + roleId + "]";
	}

	
						
}
