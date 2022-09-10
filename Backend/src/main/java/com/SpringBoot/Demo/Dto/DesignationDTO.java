package com.SpringBoot.Demo.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DesignationDTO {

	private int designationId;
	
	private String designationName;

	@Override
	public String toString() {
		return "DesignationsDTO [designationName=" + designationName + ", designationId=" + designationId + "]";
	}
			
}
