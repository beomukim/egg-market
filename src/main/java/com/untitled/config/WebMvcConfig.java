package com.untitled.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
		.addResourceHandler("/images/**") // http://localhost/images/1_1620995857660/sun.png 와 같이 접근 가능하게 매핑해준다. 
		.addResourceLocations("file:///C:\\Users\\beomukim\\Desktop\\untitled\\images/"); // 실제 파일 저장 위치
	}
}
