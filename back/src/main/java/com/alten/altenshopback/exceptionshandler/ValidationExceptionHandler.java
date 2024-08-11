package com.alten.altenshopback.exceptionshandler;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.alten.altenshopback.errorsmodel.ErrorModel;
import com.alten.altenshopback.errorsmodel.Violation;

@RestControllerAdvice
public class ValidationExceptionHandler {

	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorModel resourceNotFoundException(MethodArgumentNotValidException ex, WebRequest request) {
		System.out.println("ici");
		List<Violation> violations=new ArrayList<>();
		List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
		for (ObjectError objectError : objectErrors) {
			String fieldNameString = ((FieldError) objectError).getField();
			String messageString = objectError.getDefaultMessage();
			Violation violation=Violation
					.builder().field(fieldNameString).message(messageString).build();
			violations.add(violation);
		}
		return ErrorModel.builder().message(ex.getMessage()).status(HttpStatus.BAD_REQUEST.toString())
				.code(HttpStatus.BAD_REQUEST.value()).violations(violations).build();
	}

}
