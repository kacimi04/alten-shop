package com.alten.altenshopback;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@ComponentScan(basePackages = {"com.alten.altenshopback.resources","com.alten.altenshopback.services",
		"com.alten.altenshopback.repositories","com.alten.altenshopback.mappers",
		"com.alten.altenshopback.exceptionshandler","com.alten.altenshopback"})
class AltenShopBackApplicationTests {

	@Test
	void contextLoads() {
	}

}
