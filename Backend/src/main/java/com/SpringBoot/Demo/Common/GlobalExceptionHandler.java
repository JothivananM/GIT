package com.SpringBoot.Demo.Common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import io.jsonwebtoken.ExpiredJwtException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler
	public ResponseEntity handleExceptionTime(ExpiredJwtException ex) { // 408 Error

		APIResponse apiResponse = new APIResponse();
		apiResponse.setError("Session Expired!");
		apiResponse.setData("Redirecting to login...");
		apiResponse.setStatus(HttpStatus.REQUEST_TIMEOUT.value());
		return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT.value()).body(apiResponse);
	}

	@ExceptionHandler
	public ResponseEntity handleException(Exception e) {

		APIResponse apiResponse = new APIResponse();
		apiResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
		apiResponse.setError("Access Denied");
		// apiResponse.setData("You're not authorised to view this page.");
		apiResponse.setData(e.getMessage());
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

}
