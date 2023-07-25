package com.example.HotelManagement.Helper;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Exception to handle user not found
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Customer with id not Found")
public class CustomerNotFoundException extends Exception {

	public CustomerNotFoundException() {
		super("User not found");
	}
	

}
