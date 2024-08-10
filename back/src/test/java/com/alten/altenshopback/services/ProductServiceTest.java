package com.alten.altenshopback.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.alten.altenshopback.dto.ProductPatchDto;
import com.alten.altenshopback.dto.ProductRequestDto;
import com.alten.altenshopback.dto.ProductResponseDto;
import com.alten.altenshopback.exceptions.AltenShopNotFoundException;
import com.alten.altenshopback.models.Category;
import com.alten.altenshopback.models.InventoryStatus;
import com.alten.altenshopback.models.Product;
import com.alten.altenshopback.repositories.CategorieRepository;
import com.alten.altenshopback.repositories.ProductRepository;

@SpringBootTest
@ActiveProfiles("test")
class ProductServiceTest {

	
	@Autowired
	private ProductService productService;
	
	@MockBean
	private ProductRepository productRepository;
	
	@MockBean
	private CategorieRepository categorieRepository;
	
	
	@Test
	void saveProduct(){
		ProductRequestDto productRequestDto=ProductRequestDto
				.builder()
				.category("Accessories")
				.code("xxxxx")
				.description("description")
				.image("image1")
				.inventoryStatus("INSTOCK")
				.name("name")
				.price(Double.valueOf(13.50))
				.quantity(40)
				.rating(3)
				.build();
		Category category=Category.builder().id(1).libelle("Accessories").build();
		Product productIn=Product.builder()
				.category(category)
				.description("description")
				.image("image1")
				.inventoryStatus(InventoryStatus.INSTOCK)
				.code("xxxxx")
				.name("name")
				.quantity(40)
				.rating(3)
				.price(Double.valueOf(13.50))
				.build();
		Product productOut=Product.builder()
				.category(category)
				.description("description")
				.image("image1")
				.inventoryStatus(InventoryStatus.INSTOCK)
				.code("xxxxx")
				.name("name")
				.quantity(40)
				.rating(3)
				.id(2)
				.price(Double.valueOf(13.50))
				.build();
		when(categorieRepository.findByLibelle("Accessories")).thenReturn(Optional.of(category));
		when(productRepository.save(productIn)).thenReturn(productOut);
	ProductResponseDto responseDto=	productService.saveProduct(productRequestDto);
	assertNotNull(responseDto);
	assertEquals(2, responseDto.getId());
	assertEquals("xxxxx", responseDto.getCode());
	assertEquals("description", responseDto.getDescription());
	assertEquals("image1", responseDto.getImage());
	assertEquals(InventoryStatus.INSTOCK.value, responseDto.getInventoryStatus());
	assertEquals("name", responseDto.getName());
	assertEquals(Double.valueOf(13.50), responseDto.getPrice());
	assertEquals("40", responseDto.getQuantity());
	assertEquals(3, responseDto.getRating());
	assertEquals("Accessories", responseDto.getCategory());
	
	}
	
	@Test
	void saveProductWhenCategorieNotFound(){
		ProductRequestDto productRequestDto=ProductRequestDto
				.builder()
				.category("unknown")
				.code("xxxxx")
				.description("description")
				.image("image1")
				.inventoryStatus("INSTOCK")
				.name("name")
				.price(Double.valueOf(13.50))
				.quantity(40)
				.rating(3)
				.build();
		
		when(categorieRepository.findByLibelle("Accessories")).
		thenThrow(AltenShopNotFoundException.class);
	assertThrows(AltenShopNotFoundException.class, ()->productService.saveProduct(productRequestDto));
	}
	@Test
	void listAllProduct() {
		Category category=Category.builder().id(1).libelle("Accessories").build();
		Product product1=Product.builder()
				.category(category)
				.description("description")
				.image("image1")
				.inventoryStatus(InventoryStatus.INSTOCK)
				.code("xxxxx")
				.name("name")
				.quantity(40)
				.rating(3)
				.id(2)
				.price(Double.valueOf(13.50))
				.build();
		Product product2=Product.builder()
				.category(category)
				.description("description2")
				.image("image2")
				.inventoryStatus(InventoryStatus.INSTOCK)
				.code("yyyyy")
				.name("name2")
				.quantity(40)
				.rating(3)
				.id(3)
				.price(Double.valueOf(13.50))
				.build();
		List<Product> products=List.of(product1,product2);
		when(productRepository.findAll()).thenReturn(products);
		List<ProductResponseDto> response=productService.listAllProduct();
		assertNotNull(response);
		assertEquals(2, response.size());
		assertEquals("xxxxx", response.get(0).getCode());
		assertEquals("yyyyy", response.get(1).getCode());
		assertEquals("description", response.get(0).getDescription());
		assertEquals("description2", response.get(1).getDescription());
		assertEquals(2, response.get(0).getId());
		assertEquals(3, response.get(1).getId());
		assertEquals("image1", response.get(0).getImage());
		assertEquals("image2", response.get(1).getImage());
		assertEquals(InventoryStatus.INSTOCK.value, response.get(0).getInventoryStatus());
		assertEquals(InventoryStatus.INSTOCK.value, response.get(1).getInventoryStatus());
		assertEquals("name", response.get(0).getName());
		assertEquals("name2", response.get(1).getName());
		assertEquals(Double.valueOf(13.50), response.get(0).getPrice());
		assertEquals(Double.valueOf(13.50), response.get(1).getPrice());
		assertEquals(3, response.get(0).getRating());
		assertEquals(3, response.get(1).getRating());
		assertEquals("40", response.get(0).getQuantity());
		assertEquals("40", response.get(1).getQuantity());
		
			}
	
	@Test
	void findProduct(){
		Category category=Category.builder().id(1).libelle("Accessories").build();
		Product product1=Product.builder()
				.category(category)
				.description("description")
				.image("image1")
				.inventoryStatus(InventoryStatus.INSTOCK)
				.code("xxxxx")
				.name("name")
				.quantity(40)
				.rating(3)
				.id(2)
				.price(Double.valueOf(13.50))
				.build();
		when(productRepository.findById(2)).thenReturn(Optional.of(product1));
		ProductResponseDto response=productService.findProduct(2);
		assertNotNull(response);
		assertEquals("xxxxx", response.getCode());
		assertEquals("image1", response.getImage());
		assertEquals("description", response.getDescription());
		assertEquals(2, response.getId());
		assertEquals(InventoryStatus.INSTOCK.value, response.getInventoryStatus());
		assertEquals(Double.valueOf(13.50), response.getPrice());
	
	}
	@Test
	void findProductWhenNotFound() {
		when(productRepository.findById(1)).thenThrow(AltenShopNotFoundException.class);
		assertThrows(AltenShopNotFoundException.class, ()->productService.findProduct(1));
	}
	
	@Test 
	void deleteProductWhenNotFound() {
		when(productRepository.findById(1)).thenThrow(AltenShopNotFoundException.class);
		assertThrows(AltenShopNotFoundException.class, ()->productService.deleteProduct(1));
	}
	@Test
	void deleteProduct() {
		Category category=Category.builder().id(1).libelle("Accessories").build();
		Product product1=Product.builder()
				.category(category)
				.description("description")
				.image("image1")
				.inventoryStatus(InventoryStatus.INSTOCK)
				.code("xxxxx")
				.name("name")
				.quantity(40)
				.rating(3)
				.id(2)
				.price(Double.valueOf(13.50))
				.build();
		when(productRepository.findById(2)).thenReturn(Optional.of(product1));
		assertDoesNotThrow(()->productService.deleteProduct(2));
	}
	@Test
	void pathProduct() {
		Category category=Category.builder().id(1).libelle("Accessories").build();
		Product product1=Product.builder()
				.category(category)
				.description("description")
				.image("image1")
				.inventoryStatus(InventoryStatus.INSTOCK)
				.code("xxxxx")
				.name("name")
				.quantity(40)
				.rating(3)
				.id(2)
				.price(Double.valueOf(13.50))
				.build();
		Product product2=Product.builder()
				.category(category)
				.description("description")
				.image("image1")
				.inventoryStatus(InventoryStatus.INSTOCK)
				.code("updatedCode")
				.name("name")
				.quantity(40)
				.rating(3)
				.id(2)
				.price(Double.valueOf(13.50))
				.build();
		ProductPatchDto productPatchDto=ProductPatchDto
				.builder()
				.code("updatedCode")
				.build();
		when(productRepository.findById(2)).thenReturn(Optional.of(product1));
		when(categorieRepository.findByLibelle("Accessories")).thenReturn(Optional.of(category));
		when(productRepository.save(any())).thenReturn(product2);
		ProductResponseDto response= productService.patchProduct(2, productPatchDto);
		assertNotNull(response);
		assertEquals("updatedCode",response.getCode());
		assertEquals("name",response.getName());
		
	}
	@Test
	void pathProductWhenNotFound() {
		ProductPatchDto productPatchDto=ProductPatchDto
				.builder()
				.code("updatedCode")
				.build();
		when(productRepository.findById(1)).thenThrow(AltenShopNotFoundException.class);
		assertThrows(AltenShopNotFoundException.class, ()->productService.patchProduct(1, productPatchDto));
	}
}
