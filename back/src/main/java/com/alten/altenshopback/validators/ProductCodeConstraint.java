package com.alten.altenshopback.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = ProductCodeValidator.class)
@Target({ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ProductCodeConstraint {
	
	String message() default "Product With the Same Code existe";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
