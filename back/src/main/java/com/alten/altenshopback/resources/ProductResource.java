package com.alten.altenshopback.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alten.altenshopback.dto.ProductPatchDto;
import com.alten.altenshopback.dto.ProductRequestDto;
import com.alten.altenshopback.dto.ProductResponseDto;
import com.alten.altenshopback.services.ProductService;
import com.alten.altenshopback.validators.ProductCodeConstraint;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@RestController
@Slf4j
public class ProductResource {

	private final ProductService productService;
	
	ProductResource(ProductService productService){
		this.productService = productService;
		
	}
	
	@PostMapping("/products")
	public ResponseEntity<ProductResponseDto> addNewProduct(@RequestBody(required = true) @Valid   ProductRequestDto productDto) {
		log.info("attempt to save new product");
		return ResponseEntity
				.ok()
				.body(productService.saveProduct(productDto));
	}
	@GetMapping("/products")
	public ResponseEntity<List<ProductResponseDto>> listAllProduct(){
		log.info("attempt to retreive all products");
		return ResponseEntity
				.ok()
				.body( productService.listAllProduct());
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<ProductResponseDto> findProduct(@PathVariable(name = "id",required = true) Integer id){
		log.info("attempt to retreive product with id %d",id);
		return ResponseEntity
				.ok()
				.body( productService.findProduct(id));
	}
	
	@PatchMapping("/products/{id}")
	public ResponseEntity<ProductResponseDto> patchProduct(@PathVariable(name = "id",required = true) Integer id,@RequestBody(required = true) ProductPatchDto patchDto){
		return  ResponseEntity
				.ok().body(productService.patchProduct(id, patchDto));
	}
	@DeleteMapping("/products/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable(name="id", required = true) Integer id){
		productService.deleteProduct(id);
		return ResponseEntity.ok().build();
	}
	
	
}
