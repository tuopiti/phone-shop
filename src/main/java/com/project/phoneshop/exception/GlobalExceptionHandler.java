package com.project.phoneshop.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<?> handleHttpClientError(ApiException e){
		ErrorResponse errorResponse = new ErrorResponse(e.getStatus().getReasonPhrase(), e.getMessage());
		return ResponseEntity.status(e.getStatus())
				.body(errorResponse);
	}
	
	/*
	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<?> handleHttpClientError(HttpClientErrorException e) {
		
		ApiException exception = new ApiException(e.getStatusCode(), e.getMessage());
		return ResponseEntity.status(e.getStatusCode()).body(exception);
	}*/
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e){
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(errorResponse);
	}
	
}
