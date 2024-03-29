package com.carrots.springbootdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler
	public final ResponseEntity<Object> handleProjectIdException(ProjectIdException exception,
																			WebRequest request){
		
		ProjectIdExceptionResponse projectIdExceptionResponse = new ProjectIdExceptionResponse(exception.getMessage());
		return new ResponseEntity<Object>(projectIdExceptionResponse, HttpStatus.BAD_REQUEST);
		
	}
	
}
