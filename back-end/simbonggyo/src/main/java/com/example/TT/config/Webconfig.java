package com.example.TT.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Webconfig implements WebMvcConfigurer{
	
	@Override
	public void addCorsMappings(CorsRegistry registry)
	{
		registry.addMapping("/**")
				.allowedOrigins("http://192.168.0.19:3000")
				.allowedMethods("*");
	}
	
}
