package com.SpringBoot.Demo.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import com.SpringBoot.Demo.Dto.RequestMeta;
import com.SpringBoot.Demo.Repository.AccessLevelRepository;
import com.SpringBoot.Demo.Utill.JwtUtils;

import io.jsonwebtoken.Claims;

@Component
public class JwtInterceptor implements HandlerInterceptor {

	@Autowired
	private JwtUtils jwtUtil;

	@Autowired
	private AccessLevelRepository accessLevelRepo;

	@Autowired
	private RequestMeta requestMeta;

	// intiliza the object to create bean
	public JwtInterceptor(RequestMeta requestMeta) {
		this.requestMeta = requestMeta;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// to check the user login api then allow the response else then check the auth
		// it's null or not
		if (request.getRequestURI().contains("/user/login")) {

			return HandlerInterceptor.super.preHandle(request, response, handler);

		} else {
			String auth = request.getHeader("Authorization");
			
			if ((auth != null) && (!auth.equals(""))) {

				Claims claims = jwtUtil.verify(auth);

				requestMeta.setUserName(claims.getIssuer());
				requestMeta.setId(Integer.valueOf(claims.get("Id").toString()));
				requestMeta.setEmployeeCode(claims.get("EmployeeCode").toString());
				int roleid = requestMeta.setRoleId(Integer.valueOf(claims.get("Role-Id").toString()));

				// String warfileName = appName;
				// int fileLength = warfileName.length();
				// String RequestAllow = request.getRequestURI().substring(fileLength + 1);

				String requestAllow = request.getRequestURI();
				// String requestMethod = request.getMethod();


				if (accessLevelRepo.accessApi(requestAllow, roleid) >= 1) {
					return HandlerInterceptor.super.preHandle(request, response, handler);
				}

				throw new Exception();

			} else {
				throw new Exception();
			}
		}

	}

}