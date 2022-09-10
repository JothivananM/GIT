package com.SpringBoot.Demo.Entity;

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
@Table(name="sidebar_menu")
public class SidebarMenu {

	//define fields
			@Id
			@GeneratedValue(strategy=GenerationType.IDENTITY)
			@Column(name="role_table_id")
		    private int roleTableId;		

			@Column(name="url")
		    private String url;

			@Column(name="menu_name")
		    private String menuName;

			@Column(name="icon")
		    private String icon;

			@Column(name="is_active")
		    private Boolean isActive;

			@Override
			public String toString() {
				return "SidebarMenu [icon=" + icon + ", isActive=" + isActive + ", menuName=" + menuName
						+ ", roleTableId=" + roleTableId + ", url=" + url + "]";
			}
			
			

	}
