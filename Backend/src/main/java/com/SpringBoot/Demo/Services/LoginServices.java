package com.SpringBoot.Demo.Services;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.SpringBoot.Demo.Common.APIResponse;
import com.SpringBoot.Demo.Dto.LoginRequestDTO;
import com.SpringBoot.Demo.Entity.UserDetails;
import com.SpringBoot.Demo.Repository.UserDetailsRepository;
import com.SpringBoot.Demo.Utill.JwtUtils;


@Service
public class LoginServices {

	@Autowired
	private UserDetailsRepository userDetailsRepo;

	@Autowired
	private JwtUtils jwtUtil;
	
	//Checks the incoming Username and Password if it is correct
	public APIResponse login(LoginRequestDTO loginDto) {

		APIResponse apiResponse = new APIResponse();

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		String UserPassword = userDetailsRepo.getUserPassword(loginDto.getCompanyEmailId());

		if (passwordEncoder.matches(loginDto.getPassword(), UserPassword)) {

			UserDetails User = userDetailsRepo
					.findOneBycompanyEmailIdIgnoreCaseAndUserPassword(loginDto.getCompanyEmailId(), UserPassword);

			// and check the values null or not
			if (User == null) {
				apiResponse.setData("user login failed");
			}

			// if it's not null then generate token
			String token = jwtUtil.generateJwt(User);

			Map<String, Object> data = new HashMap<>();
			data.put("accessToken", token);

			// then that token set in the api response obejct
			apiResponse.setData(data);
			return apiResponse;
		}
		apiResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
		apiResponse.setData("Please check your credentials and try again.");
		apiResponse.setError("Login is failed");
		// to pass the controller
		return apiResponse;
	}

}
