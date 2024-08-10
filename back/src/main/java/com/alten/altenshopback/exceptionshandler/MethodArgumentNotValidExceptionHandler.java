package com.alten.altenshopback.exceptionshandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.alten.altenshopback.errorsmodel.ErrorModel;

@RestControllerAdvice
public class MethodArgumentNotValidExceptionHandler {
	
	  @ExceptionHandler(value = {MethodArgumentNotValidException.class})
	  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
	  public ErrorModel resourceNotFoundException(MethodArgumentNotValidException ex, WebRequest request) {
	    return ErrorModel.builder()
	    		.message(ex.getMessage())
	    		.status(HttpStatus.BAD_REQUEST.toString())
	    		.code(HttpStatus.BAD_REQUEST.value())
	    		.build();
	  }

}
