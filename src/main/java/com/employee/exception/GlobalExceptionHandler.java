package com.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.employee.dto.ApiResponce;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponce> resourceNotFoundException(ResourceNotFoundException ex)
	{
		String message=ex.getMessage();
		ApiResponce apiResponce=new ApiResponce(message,false);
		return new ResponseEntity<ApiResponce>(apiResponce,HttpStatus.NOT_FOUND);
		
	}
}
