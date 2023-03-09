package com.nichi.ecommerce.exceptions;

public class AuthenticationFailException extends IllegalArgumentException{
	public AuthenticationFailException(String msg) {
		super(msg);
	}
}
