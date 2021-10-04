package com.memo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.memo.interceptor.PermissionInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Autowired 
	private PermissionInterceptor interceptor;
	

	
	@Override 
	public void addInterceptors(InterceptorRegistry registry) { 
		registry.addInterceptor(interceptor) 
		.addPathPatterns("/**") // ** 아래 디렉토리까지 확인
		.excludePathPatterns("/user/sign_out", "/static/**", "/error"); // 예외처리
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**") // localhost/images/sadf_123123/apple.png
		.addResourceLocations("file:///C:\\Users\\beomukim\\Desktop\\workspace-memo\\images/"); // 실제 파일 저장 위치
	}
}
