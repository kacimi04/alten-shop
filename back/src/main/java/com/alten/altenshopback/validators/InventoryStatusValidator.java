package com.alten.altenshopback.validators;

import com.alten.altenshopback.models.InventoryStatus;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class InventoryStatusValidator implements 
ConstraintValidator<InventoryStatusConstraint, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		 if( value!=null && value.length()> 0 &&  InventoryStatus.valueOf(value)!=null) {
			return true;
		}
		 log.error(String.format("Inventory Status %s is not a valid status", value));
	return false;
	}

}
