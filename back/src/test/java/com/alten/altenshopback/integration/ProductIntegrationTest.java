package com.alten.altenshopback.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.alten.altenshopback.dto.ProductRequestDto;
import com.alten.altenshopback.models.InventoryStatus;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.api.DBRider;
import com.google.gson.Gson;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@DBRider 
@DBUnit(cacheConnection = false, leakHunter = true)
@DataSet(value = {"product.yml","category.yml"})
public class ProductIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void addProduct() throws Exception {
		ProductRequestDto dto=ProductRequestDto
				.builder()
				.category("category2")
				.code("xxxx")
				.inventoryStatus(InventoryStatus.LOWSTOCK.value)
				.name("name")
				.price(Double.valueOf(20))
				.quantity(34)
				.build();
	 mvc.perform(post("/products").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(dto))
				).andExpect(status().isOk())
			    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
			    .andExpect(jsonPath("$.code", is(dto.getCode())))
			    .andExpect(jsonPath("$.name", is(dto.getName())))
			    .andExpect(jsonPath("$.description", is(dto.getDescription())))
			    .andExpect(jsonPath("$.price", is(dto.getPrice())))
			    .andExpect(jsonPath("$.quantity", is(dto.getQuantity().toString())))
			    .andExpect(jsonPath("$.category", is(dto.getCategory())));
	}
	@Test
	void addProductWhenCodeExist() throws Exception {
		ProductRequestDto dto=ProductRequestDto
				.builder()
				.category("category2")
				.code("code")
				.inventoryStatus(InventoryStatus.LOWSTOCK.value)
				.name("name")
				.price(Double.valueOf(20))
				.quantity(34)
				.build();
	 mvc.perform(post("/products").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(dto))
				).andExpect(status().is(400));      
	}
	
	@Test
	void addProductWhenStatusInventoryNotFound() throws Exception {
		ProductRequestDto dto=ProductRequestDto
				.builder()
				.category("category2")
				.code("code")
				.inventoryStatus(InventoryStatus.LOWSTOCK.value)
				.name("name")
				.price(Double.valueOf(20))
				.quantity(34)
				.build();
	 mvc.perform(post("/products").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(dto))
				).andExpect(status().is(400));      
	}
	
	@Test
	void getAllProducts() throws Exception {
		mvc.perform(get("/products").contentType(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk())
			    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
			    .andExpect(jsonPath("$[0].code", is("code")))
			    .andExpect(jsonPath("$[0].name", is("name")))
			    .andExpect(jsonPath("$[0].description", is("description")))
			    .andExpect(jsonPath("$[0].price", is(20.0)))
			    .andExpect(jsonPath("$[0].quantity", is("3")))
			    .andExpect(jsonPath("$[0].category", is("category1")));     
	}
	
	@Test
	void getProductsById() throws Exception {
		mvc.perform(get("/products/1").contentType(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk())
			    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
			    .andExpect(jsonPath("$.code", is("code")))
			    .andExpect(jsonPath("$.name", is("name")))
			    .andExpect(jsonPath("$.description", is("description")))
			    .andExpect(jsonPath("$.price", is(20.0)))
			    .andExpect(jsonPath("$.quantity", is("3")))
			    .andExpect(jsonPath("$.category", is("category1")));     
	}
	
	@Test
	void getProductsByIdWhenNotFound() throws Exception {
		mvc.perform(get("/products/2").contentType(MediaType.APPLICATION_JSON)
				).andExpect(status().isNotFound());
			    
	}
	
	@Test
	void patchProductWhenNotFound() throws Exception {
		ProductRequestDto dto=ProductRequestDto
				.builder()
				.category("category2")
				.code("xxxx")
				.inventoryStatus(InventoryStatus.LOWSTOCK.value)
				.name("name")
				.price(Double.valueOf(20))
				.quantity(34)
				.build();
		MockHttpServletRequestBuilder builder =
	              MockMvcRequestBuilders.patch("/products/" + 3)
	                                    .contentType(MediaType.APPLICATION_JSON)
	                                    .accept(MediaType.APPLICATION_JSON)
	                                    .characterEncoding("UTF-8")
	                                    .content(new Gson().toJson(dto));
		mvc.perform(builder)
		.andExpect(status().is(404));
			    
	}
	@Test
	void patchProduct() throws Exception {
		ProductRequestDto dto=ProductRequestDto
				.builder()
				.category("category2")
				.code("xxxx")
				.inventoryStatus(InventoryStatus.LOWSTOCK.value)
				.name("name")
				.price(Double.valueOf(20))
				.quantity(34)
				.build();
		MockHttpServletRequestBuilder builder =
	              MockMvcRequestBuilders.patch("/products/" + 1)
	                                    .contentType(MediaType.APPLICATION_JSON)
	                                    .accept(MediaType.APPLICATION_JSON)
	                                    .characterEncoding("UTF-8")
	                                    .content(new Gson().toJson(dto));
		mvc.perform(builder)
		.andExpect(status().is(200))
		.andExpect(jsonPath("$.code", is("xxxx")))
	    .andExpect(jsonPath("$.name", is("name")))
	    .andExpect(jsonPath("$.description", is("description")))
	    .andExpect(jsonPath("$.price", is(20.0)))
	    .andExpect(jsonPath("$.quantity", is("34")))
	    .andExpect(jsonPath("$.category", is("category2"))); 
			    
	}
	@Test
	void deleteProduct() {
		
	}
}
