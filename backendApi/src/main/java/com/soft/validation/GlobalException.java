package com.soft.validation;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(ExistByEmail.class)
	public ResponseEntity<ErrorMsg> userNotExistException(){
		ErrorMsg message= new ErrorMsg(404, "Email Already Exist", new Date());
		ResponseEntity<ErrorMsg> response= new ResponseEntity<ErrorMsg>(message,HttpStatus.NOT_FOUND);
		return response;
	}
	
	@ExceptionHandler(ExistById.class)
	public ResponseEntity<ErrorMsg> IdNotFound(){
		ErrorMsg message= new ErrorMsg(404,"Id Not Found",new Date());
		ResponseEntity<ErrorMsg> response= new ResponseEntity<ErrorMsg>(message,HttpStatus.NOT_FOUND);
		return response;
	}
	
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<ErrorMsg> handleInvalidCredentials(){
		ErrorMsg message= new ErrorMsg(404,"Invalid email or password",new Date());
		ResponseEntity<ErrorMsg> response= new ResponseEntity<ErrorMsg>(message,HttpStatus.NOT_FOUND);
		return response;
	}
	
	
}
