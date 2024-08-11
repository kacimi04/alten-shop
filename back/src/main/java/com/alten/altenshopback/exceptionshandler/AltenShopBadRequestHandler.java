package com.alten.altenshopback.exceptionshandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.alten.altenshopback.errorsmodel.ErrorModel;
import com.alten.altenshopback.exceptions.AltenShopNotFoundException;

@RestControllerAdvice
public class AltenShopBadRequestHandler {
	
	  @ExceptionHandler(value = {AltenShopNotFoundException.class})
	  @ResponseStatus(value = HttpStatus.NOT_FOUND)
	  public ErrorModel resourceNotFoundException(AltenShopNotFoundException ex, WebRequest request) {
		  System.out.println("here1");
	    return ErrorModel.builder()
	    		.message(ex.getMessage())
	    		.status(HttpStatus.NOT_FOUND.toString())
	    		.code(HttpStatus.NOT_FOUND.value())
	    		.build();
	  }

}
