package com.carrots.springbootdemo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class ValidationMapService {

	public ResponseEntity<?> mapValidation(BindingResult result){
		Map<String, String> errorMap = new HashMap<String, String>();
		for (FieldError error : result.getFieldErrors()) {
			errorMap.put(error.getField(), error.getDefaultMessage());
		}
		return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
	}
	
}
