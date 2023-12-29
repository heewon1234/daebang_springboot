package com.kdt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/uploads/board/**").addResourceLocations("file:/uploads/board/");
		registry.addResourceHandler("/uploads/temp_review/**").addResourceLocations("file:/uploads/temp_review/");
		registry.addResourceHandler("/uploads/review/**").addResourceLocations("file:/uploads/review/");
		registry.addResourceHandler("/uploads/estateImages/**").addResourceLocations("file:/uploads/estateImages/");
		registry.addResourceHandler("/uploads/agentProfile/**").addResourceLocations("file:/uploads/agentProfile/");
	}
	
}
