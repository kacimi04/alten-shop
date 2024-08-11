package com.alten.altenshopback.exceptionshandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.alten.altenshopback.errorsmodel.ErrorModel;

@RestControllerAdvice
public class ValidationExceptionHandler {

	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorModel resourceNotFoundException(MethodArgumentNotValidException ex, WebRequest request) {
		Map<String, String> violations =new HashMap<String,String>();
		List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
		for (ObjectError objectError : objectErrors) {
			String fieldNameString = ((FieldError) objectError).getField();
			String messageString = objectError.getDefaultMessage();
			violations.put(fieldNameString, messageString);
		}
		return ErrorModel.builder().message(ex.getMessage()).status(HttpStatus.BAD_REQUEST.toString())
				.code(HttpStatus.BAD_REQUEST.value()).violations(violations).build();
	}

}
