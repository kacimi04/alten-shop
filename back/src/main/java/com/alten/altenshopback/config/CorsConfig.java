package com.alten.altenshopback.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@PropertySource("classpath:application-${spring.profiles.active}.properties")
public class CorsConfig {

	@Value("${allowed.cors.origins}")
	private String corsOrigins;

	@Bean
	public Filter corsFilter() {
		return new Filter() {

			@Override
			public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
					throws IOException, ServletException {
				HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.setHeader("Access-Control-Allow-Origin", corsOrigins); 
                httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE,PATCH , OPTIONS");
                httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type");
                chain.doFilter(request, response);
				
			}
			
		};
		
	}
}
