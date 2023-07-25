package com.example.HotelManagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.HotelManagement.entity.Customer;
import com.example.HotelManagement.service.CustomerService;

@RestController
//@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	//Add new customer
	@PostMapping("/api/customer")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) throws Exception{
		Customer customer1 = this.customerService.addCustomer(customer);
		return ResponseEntity.status(HttpStatus.CREATED).body(customer1);
	}
	
	
	 	//get customer by id
	    @GetMapping("/api/customer/{customerId}")
		public Customer getCustomer(@PathVariable("customerId")Long customerId) throws Exception{
			return customerService.getCustomer(customerId);
		}
		
	    //get all customers
		@GetMapping("/")
		public ResponseEntity<?> getCustomers(){
			return ResponseEntity.ok(this.customerService.getCustomers());
		}
		
		//update existing customers
		@PutMapping("/")
		public Customer updateCustomer(@RequestBody Customer customer) {
			return this.customerService.updateCustomer(customer);
		}
		
		//delete customer by id
		@DeleteMapping("/{customerId}")
		public void deleteCustomer(@PathVariable("customerId")Long customerId) {
			this.customerService.deleteCustomer(customerId);
		}
	 
}
