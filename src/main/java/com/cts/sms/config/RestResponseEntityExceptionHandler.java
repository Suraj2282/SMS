package com.cts.sms.config;

import java.sql.SQLIntegrityConstraintViolationException;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cts.sms.entity.ErrorClass;
import com.cts.sms.exception.ParentsNotFoundException;
import com.cts.sms.exception.StudentNotFoundException;
import com.cts.sms.exception.TeacherNotFoundException;

@RestControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<ErrorClass> studentNotFoundException(StudentNotFoundException exception, WebRequest request)
	{
		ErrorClass message=new ErrorClass(HttpStatus.NOT_FOUND,exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	}
	
	@ExceptionHandler(TeacherNotFoundException.class)
	public ResponseEntity<ErrorClass> teacherNotFoundException(TeacherNotFoundException exception, WebRequest request)
	{
		ErrorClass message = new ErrorClass(HttpStatus.NOT_FOUND, exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	}
	
	@ExceptionHandler(ParentsNotFoundException.class)
	public ResponseEntity<ErrorClass> parentsNotFoundException(ParentsNotFoundException exception, WebRequest request)
	{
		ErrorClass message = new ErrorClass(HttpStatus.NOT_FOUND,exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	}
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ErrorClass> duplicateValueFoundException(SQLIntegrityConstraintViolationException exception, WebRequest request)
	{
		ErrorClass message = new ErrorClass(HttpStatus.INTERNAL_SERVER_ERROR,exception.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
	}
	
	
	
	 
	

}
