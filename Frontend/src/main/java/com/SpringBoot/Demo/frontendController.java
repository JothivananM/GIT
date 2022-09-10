package com.SpringBoot.Demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class frontendController {
	
	@RequestMapping("/*")
	public String sidebarPage(){
		return "index";
	}	

	@RequestMapping("/login")
	public String showLoginPage() {
		return "login";
	}

	@RequestMapping("/templates/404.html")
	public String show404page() {
		return "404";
	}

	@RequestMapping("/templates/admin.html")
	public String showAdminpage() {
		return "admin";
	}

	@RequestMapping("/templates/list.html")
	public String showListPage() {
		return "list";
	}

	@RequestMapping("/templates/create.html")
	public String showCreatePage() {
		return "create";
	}

	@RequestMapping("/templates/employee.html")
	public String showEmployeePage() {
		return "employee";
	}

	@RequestMapping("/templates/timesheet-management.html")
	public String showTimesheetManagementPage() {
		return "timesheet-management";
	}

	@RequestMapping("/templates/profile.html")
	public String showProfilePage() {
		return "profile";
	}

	@RequestMapping("/templates/change-password.html")
	public String showChangePasswordPage() {
		return "change-password";
	}
	
}
