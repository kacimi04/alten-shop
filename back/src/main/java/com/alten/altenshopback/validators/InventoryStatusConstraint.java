package com.alten.altenshopback.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = InventoryStatusValidators.class)
@Target({ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface InventoryStatusConstraint {
	
	String message() default "Invalid Inventory Status";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
