package com.kdt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/uploads/board/**").addResourceLocations("file:C:/uploads/board/");
		registry.addResourceHandler("/uploads/temp_review/**").addResourceLocations("file:C:/uploads/temp_review/");
		registry.addResourceHandler("/uploads/review/**").addResourceLocations("file:C:/uploads/review/");
		registry.addResourceHandler("/uploads/estateImages/**").addResourceLocations("file:C:/uploads/estateImages/");
		registry.addResourceHandler("/uploads/agentProfile/**").addResourceLocations("file:C:/uploads/agentProfile/");
	}
	
}
