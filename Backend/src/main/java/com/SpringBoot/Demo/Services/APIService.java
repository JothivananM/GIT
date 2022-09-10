package com.SpringBoot.Demo.Services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.SpringBoot.Demo.Dto.RequestMeta;
import com.SpringBoot.Demo.Dto.UserDetailsDTO;
import com.SpringBoot.Demo.Entity.Designation;
import com.SpringBoot.Demo.Entity.Role;
import com.SpringBoot.Demo.Entity.SidebarMenu;
import com.SpringBoot.Demo.Entity.UserDetails;

import com.SpringBoot.Demo.Mapper.UserDetailsMapper;
import com.SpringBoot.Demo.Repository.DesignationRepository;
import com.SpringBoot.Demo.Repository.RoleRepository;
import com.SpringBoot.Demo.Repository.SidebarMenuRepository;
import com.SpringBoot.Demo.Repository.UserDetailsRepository;

@Service
public class APIService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

   @Autowired
	private UserDetailsMapper userDetailsMapper;

    @Autowired
    private SidebarMenuRepository sidebarMenuRepository;

    @Autowired
	private RequestMeta requestMeta;

    @Autowired
	private DesignationRepository designationRepository;

    @Autowired
    private RoleRepository roleRepository;

    // UserDetails Enntity Service
    //Gets List of users
    public List<UserDetails> findAllUsers() {

        return userDetailsRepository.findAll();
    }

    // Get specific user by id
    public UserDetails findUserById(int id) {
        
        return userDetailsRepository.findById(id).orElse(null);
    }

    // Saves new user
    public UserDetailsDTO saveUser(UserDetails userDetails) {
        
        userDetails.setId(0);

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		String password = userDetails.getMobileNo();

		String encodedPassword = passwordEncoder.encode(password);

		userDetails.setUserPassword(encodedPassword);

		return userDetailsMapper.modelToDto(userDetailsRepository.save(userDetails));
    }

    // Update user
    public UserDetails updateUser(UserDetails userDetails) {   
        
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		String updatePassword =userDetails.getMobileNo();

		String encodedPassword = passwordEncoder.encode(updatePassword);

		userDetails.setUserPassword(encodedPassword);

		return userDetailsRepository.save(userDetails);

    }

    // Soft delete user (change is_active status)
    public int deleteUser(int id, Boolean status) {
       
         return userDetailsRepository.updateStatus(id, status);
        
    }

    // SidebarMenu Entity service
    // Gets all menu List
    public List<SidebarMenu> findAllMenu() {
        return sidebarMenuRepository.findAll();
    }

    public List<SidebarMenu> findListByRoleId() {
        return sidebarMenuRepository.findAllByRoleId(requestMeta.getRoleId());
    }

    // Designation Entity Service
    // Get all designations List
    public List<Designation> findAllDesignations() {
        return designationRepository.findAll();
    }

    // Role Entity Service
    // Get all Role List
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

}
