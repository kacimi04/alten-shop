package com.alten.altenshopback.validators;

import com.alten.altenshopback.dto.ProductRequestDto;
import com.alten.altenshopback.repositories.ProductRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

import com.alten.altenshopback.exceptions.AltenShopBadRequestException;
@Slf4j
public class ProductCodeValidator implements 
ConstraintValidator<ProductCodeConstraint, ProductRequestDto>{
	
	private final ProductRepository productRepository;
	
	ProductCodeValidator(ProductRepository productRepository){
		this.productRepository = productRepository;
		
	}
	

	@Override
	public boolean isValid(ProductRequestDto dto, ConstraintValidatorContext context) {
		if(productRepository.findByCode(dto.getCode()).isEmpty()) {
			return true;
		}
		log.error(String.format("Product with code %S alerady exist , unique constraint violations", dto.getCode()));
		throw new AltenShopBadRequestException(String.format("Product with code %S alerady exist , unique constraint violations",
				dto.getCode()));
	}

}
