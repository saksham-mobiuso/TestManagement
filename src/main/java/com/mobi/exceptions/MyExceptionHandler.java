package com.mobi.exceptions;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(value = {NoCorrectOptionError.class})
	public ResponseEntity<NoCorrectOptionError> handleAnyException(Exception ex, WebRequest webRequest){
		
		NoCorrectOptionError errorMessage = new NoCorrectOptionError(new Date(), "No Correct Option");
		
		
		return new ResponseEntity<NoCorrectOptionError>(
				errorMessage, HttpStatus.NOT_FOUND);
		
	}
	
}
