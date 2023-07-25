package com.example.HotelManagement.Helper;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Password doesn't matcht the criteria")
public class PasswordMismatchException extends Exception{

	public PasswordMismatchException() {
		super("Password doesn't match the criteria");
		
	}
	

}
