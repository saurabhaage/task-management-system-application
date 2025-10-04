package com.soft.validation;

public class InvalidCredentialsException  extends RuntimeException{

	InvalidCredentialsException(String msg){
		super(msg);
	}
}
