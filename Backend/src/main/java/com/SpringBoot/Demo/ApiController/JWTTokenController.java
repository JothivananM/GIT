package com.SpringBoot.Demo.ApiController;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.SpringBoot.Demo.Common.APIResponse;
import com.SpringBoot.Demo.Dto.DesignationDTO;
import com.SpringBoot.Demo.Dto.LoginRequestDTO;
import com.SpringBoot.Demo.Dto.RequestMeta;
import com.SpringBoot.Demo.Dto.RoleDTO;
import com.SpringBoot.Demo.Dto.SidebarMenuDTO;
import com.SpringBoot.Demo.Dto.UserDetailsDTO;
import com.SpringBoot.Demo.Entity.UserDetails;
import com.SpringBoot.Demo.Mapper.DesignationMapper;
import com.SpringBoot.Demo.Mapper.RoleMapper;
import com.SpringBoot.Demo.Mapper.SidebarMenuMapper;
import com.SpringBoot.Demo.Mapper.UserDetailsMapper;
import com.SpringBoot.Demo.Repository.AccessLevelRepository;
import com.SpringBoot.Demo.Services.APIService;
import com.SpringBoot.Demo.Services.LoginServices;

@RestController
public class JWTTokenController {

	@Autowired
	private LoginServices loginService;

	@Autowired
	private APIService service;

	@Autowired
	private AccessLevelRepository accessLevelRepo;

	@Autowired
	private RequestMeta requestMeta;

	@Autowired
	private UserDetailsMapper mapper;

	@Autowired
	private SidebarMenuMapper sidebarMenuMapper;

	@Autowired
	private DesignationMapper designationMapper;

	@Autowired
	private RoleMapper roleMapper;

	// Creates token for every user login using this api
	@PostMapping("user/login")
	public ResponseEntity<APIResponse> login(@RequestBody LoginRequestDTO loginRequestDTO) {

		APIResponse apiResponse = loginService.login(loginRequestDTO);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);

	}

	// Role Entity APIs
	// List all roles
	@GetMapping("role")
	public List<RoleDTO> getRole() {
		return roleMapper.modelsToDtos(service.findAllRoles());
	}

	// Designation entity APIs
	// List all designations
	@GetMapping("designation")
	public List<DesignationDTO> getAlldesignations() {
		return designationMapper.modelsToDtos(service.findAllDesignations());
	}
	
	// UserdDetails Entity APIs
	// Add new user
	@PostMapping("user")
	public UserDetailsDTO addUserController(@RequestBody UserDetailsDTO dto) {
        
		return service.saveUser(mapper.dtoToModel(dto));
	}

	// List all users
	@GetMapping("user")
	public List<UserDetailsDTO> listAlluserDetails(){
		
		return mapper.modelsToDtos(service.findAllUsers());
	}

	// List a specific user
	@RequestMapping(value = "user/id", method = RequestMethod.GET)
    public UserDetailsDTO findOneUserDetails(@RequestParam("id") int userId) {
        return mapper.modelToDto(service.findUserById(userId));
    }

	// Update user details
	@PutMapping("user")
	public UserDetailsDTO updateUserDetails (@RequestBody UserDetailsDTO dto) {

		UserDetails userDetails = mapper.dtoToModel(dto);

		return mapper.modelToDto(service.updateUser(userDetails));

	}

	// Soft delete user
	@DeleteMapping("user")
	public int deleteUserDetails(@RequestBody UserDetailsDTO dto) {
		UserDetails userDetails = mapper.dtoToModel(dto);
		int temp = service.deleteUser(userDetails.getId(), userDetails.getIsActive());
		// mapper.modelToDto();	
		return temp;
	}

	@GetMapping("role/roleId")
	public int getRoleIdNavbar() {
		int roleid = requestMeta.getRoleId();
		return roleid;
	}

	// SidebarMenu Entity APIs
	// Gets list of Sidebar menues
	@GetMapping("sidebar")
	public List<SidebarMenuDTO> getSidebarMenuList() {
		return sidebarMenuMapper.modelsToDtos(service.findAllMenu());
	}

	// Gets list of specific id
	@GetMapping("sidebar/id")
	public List<SidebarMenuDTO> getSidebarMenuRoleId() {
		return sidebarMenuMapper.modelsToDtos(service.findListByRoleId());
	}

	// Access level APIs
	// Checks if the user has the access for thr API
	@GetMapping("user/access")
	public Boolean accessApis(@RequestParam("url") String url) {
		if (accessLevelRepo.accessApi(url, requestMeta.getRoleId()) == 1) {
			return true;
		}
		return false;
	}


}
