package com.kdt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	private final String bucketName = "daebbang";
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/board/**").addResourceLocations("file:https://storage.googleapis.com/"+bucketName+"/board");
		registry.addResourceHandler("/temp_review/**").addResourceLocations("file:https://storage.googleapis.com/"+bucketName+"/temp_review");
		registry.addResourceHandler("/review/**").addResourceLocations("file:https://storage.googleapis.com/"+bucketName+"/review");
		registry.addResourceHandler("/uploads/estateImages/**").addResourceLocations("file:/uploads/estateImages/");
		registry.addResourceHandler("/uploads/agentProfile/**").addResourceLocations("file:/uploads/agentProfile/");
	}
	
}
