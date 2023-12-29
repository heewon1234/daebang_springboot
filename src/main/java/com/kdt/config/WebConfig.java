package com.kdt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/uploads/board/**").addResourceLocations("/uploads/board/");
		registry.addResourceHandler("/uploads/temp_review/**").addResourceLocations("/uploads/temp_review/");
		registry.addResourceHandler("/uploads/review/**").addResourceLocations("/uploads/review/");
		registry.addResourceHandler("/uploads/estateImages/**").addResourceLocations("/uploads/estateImages/");
		registry.addResourceHandler("/uploads/agentProfile/**").addResourceLocations("/uploads/agentProfile/");
	}
	
}
