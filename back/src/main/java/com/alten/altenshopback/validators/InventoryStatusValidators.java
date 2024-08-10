package com.alten.altenshopback.validators;

import com.alten.altenshopback.models.InventoryStatus;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class InventoryStatusValidators implements 
ConstraintValidator<InventoryStatusConstraint, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
	   return InventoryStatus.valueOf(value)!=null;
	}

}
