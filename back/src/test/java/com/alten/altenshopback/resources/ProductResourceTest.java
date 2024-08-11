package com.alten.altenshopback.resources;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.alten.altenshopback.dto.ProductPatchDto;
import com.alten.altenshopback.dto.ProductRequestDto;
import com.alten.altenshopback.dto.ProductResponseDto;
import com.alten.altenshopback.exceptions.AltenShopNotFoundException;
import com.alten.altenshopback.models.InventoryStatus;
import com.alten.altenshopback.repositories.ProductRepository;
import com.alten.altenshopback.services.ProductService;

@SpringBootTest
@ActiveProfiles("test")
class ProductResourceTest {

	@Autowired
	private ProductResource productResource;

	@MockBean
	private ProductService productService;
	
	@MockBean
	private ProductRepository productRepository;
	
	

	@Test
	void  addnewProduct() {
		ProductRequestDto dto=ProductRequestDto
				.builder()
				.category("Clothing")
				.code("xxxx")
				.inventoryStatus(InventoryStatus.LOWSTOCK.value)
				.name("name")
				.price(Double.valueOf(20))
				.quantity(34)
				.build();
		ProductResponseDto responsDto=ProductResponseDto.builder()
				.category("Clothing")
				 .code("xxxx")
				.inventoryStatus(InventoryStatus.LOWSTOCK.value)
				.name("name")
				.price(Double.valueOf(20))
				.quantity("34")
				.build();
	 when(productService.saveProduct(dto)).thenReturn(responsDto);
	 ResponseEntity<ProductResponseDto> response=	productResource.addNewProduct(dto);
	 assertNotNull(response.getBody());
	 assertEquals("Clothing", response.getBody().getCategory());
	 assertEquals("xxxx", response.getBody().getCode());
	 assertEquals(InventoryStatus.LOWSTOCK.value, response.getBody().getInventoryStatus());
	 assertEquals("name", response.getBody().getName());
	 assertEquals(Double.valueOf(20), response.getBody().getPrice());
	 assertEquals("34", response.getBody().getQuantity());
	}
	
	
	@Test
	void getAllProdut() {
		ProductResponseDto responsDto=ProductResponseDto.builder()
				.category("Clothing")
				 .code("xxxx")
				.inventoryStatus(InventoryStatus.LOWSTOCK.value)
				.name("name")
				.price(Double.valueOf(20))
				.quantity("34")
				.build();
	when(productService.listAllProduct()).thenReturn(List.of(responsDto));
	ResponseEntity<List<ProductResponseDto>> response=productResource.listAllProduct();
	 assertNotNull(response);
	 assertNotNull(response.getBody());
	 assertEquals(1, response.getBody().size());
	 assertEquals("Clothing", response.getBody().get(0).getCategory());
	 assertEquals("xxxx", response.getBody().get(0).getCode());
	 assertEquals(InventoryStatus.LOWSTOCK.value, response.getBody().get(0).getInventoryStatus());
	 assertEquals("name", response.getBody().get(0).getName());
	 assertEquals(Double.valueOf(20), response.getBody().get(0).getPrice());
	 assertEquals("34", response.getBody().get(0).getQuantity());
	}
	
	@Test
	void getProductById() {
		ProductResponseDto responsDto=ProductResponseDto.builder()
				.category("Clothing")
				 .code("xxxx")
				.inventoryStatus(InventoryStatus.LOWSTOCK.value)
				.name("name")
				.price(Double.valueOf(20))
				.quantity("34")
				.build();
		when(productService.findProduct(1)).thenReturn(responsDto);
		ResponseEntity<ProductResponseDto> response=	productResource.findProduct(1);
		 assertNotNull(response.getBody());
		 assertEquals("Clothing", response.getBody().getCategory());
		 assertEquals("xxxx", response.getBody().getCode());
		 assertEquals(InventoryStatus.LOWSTOCK.value, response.getBody().getInventoryStatus());
		 assertEquals("name", response.getBody().getName());
		 assertEquals(Double.valueOf(20), response.getBody().getPrice());
		 assertEquals("34", response.getBody().getQuantity());
		
		 
	}
	@Test
	void  patchProduct() {
		ProductPatchDto dto=ProductPatchDto
				.builder()
				.category("Clothing")
				.code("xxxx")
				.inventoryStatus(InventoryStatus.LOWSTOCK.value)
				.name("name")
				.price(Double.valueOf(20))
				.quantity(34)
				.build();
		ProductResponseDto responsDto=ProductResponseDto.builder()
				.category("Clothing")
				 .code("modifiedCode")
				.inventoryStatus(InventoryStatus.INSTOCK.value)
				.name("namemodified")
				.price(Double.valueOf(20))
				.quantity("45")
				.build();
	 when(productService.patchProduct(1,dto)).thenReturn(responsDto);
	 ResponseEntity<ProductResponseDto> response=	productResource.patchProduct(1, dto);
	 assertNotNull(response.getBody());
	 assertEquals("Clothing", response.getBody().getCategory());
	 assertEquals("modifiedCode", response.getBody().getCode());
	 assertEquals(InventoryStatus.INSTOCK.value, response.getBody().getInventoryStatus());
	 assertEquals("namemodified", response.getBody().getName());
	 assertEquals(Double.valueOf(20), response.getBody().getPrice());
	 assertEquals("45", response.getBody().getQuantity());
	}
	
	@Test
	void deleteProductWhenNotFound() {
		doThrow(new AltenShopNotFoundException("exception") ).when(productService).deleteProduct(3);
		assertThrows(AltenShopNotFoundException.class, ()->productResource.deleteProduct(3));
	}
	
	@Test
	void deleteProduct() {
		assertDoesNotThrow(()->productResource.deleteProduct(3));
	}
}
