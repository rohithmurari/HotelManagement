package com.example.HotelManagement.Helper;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

//Exception to handle duplicate email entries
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Customer with email Found")
public class CustomerFoundException extends Exception{

	public CustomerFoundException() {
		super("Customer with this email already exists");
		
	}

}
