package com.alten.altenshopback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.alten.altenshopback.resources","com.alten.altenshopback.services",
		"com.alten.altenshopback.repositories","com.alten.altenshopback.mappers",
		"com.alten.altenshopback.exceptionshandler","com.alten.altenshopback.config","com.alten.altenshopback"})
public class AltenShopBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(AltenShopBackApplication.class, args);
	}

}
