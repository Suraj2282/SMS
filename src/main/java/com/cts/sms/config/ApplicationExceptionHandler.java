package com.cts.sms.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {
	
	 @ResponseStatus(HttpStatus.BAD_REQUEST)
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	 public Map<String, String> handleInvalidArguments(MethodArgumentNotValidException ex)
	 {
		 Map<String, String> exceptionMap = new HashMap<>();
		 ex.getBindingResult().getFieldErrors().forEach(error -> {
			 exceptionMap.put(error.getField(), error.getDefaultMessage());
		 });
		 return exceptionMap;
	 }
	 

}
