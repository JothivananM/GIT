package com.SpringBoot.Demo.Common;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.SpringBoot.Demo.Dto.RequestMeta;
import com.SpringBoot.Demo.Interceptor.JwtInterceptor;

@Configuration
public class CustomWebConfig implements WebMvcConfigurer {

	@Autowired
	private JwtInterceptor jwtInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor);
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedHeaders(Arrays.asList("*"));
		config.setAllowedOrigins(Arrays.asList("http://localhost:8080/","http://localhost:8081/","http://localhost:8082/", "http://localhost:8083/"));
		config.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE"));
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
	

	@Bean
	@RequestScope
	public RequestMeta getRequestMeta() {
		return new RequestMeta();
	}

	@Bean
	public JwtInterceptor Interceptor() {
		return new JwtInterceptor(getRequestMeta());
	}

}
