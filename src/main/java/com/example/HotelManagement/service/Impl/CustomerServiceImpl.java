package com.example.HotelManagement.service.Impl;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.HotelManagement.Helper.CustomerFoundException;
import com.example.HotelManagement.Helper.CustomerNotFoundException;
import com.example.HotelManagement.Helper.PasswordMismatchException;
import com.example.HotelManagement.Repo.CustomerRepository;
import com.example.HotelManagement.entity.Customer;
import com.example.HotelManagement.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	//Method to create customer
	@Override
	public Customer addCustomer(Customer customer) throws Exception {
		Customer local = customerRepository.findByEmail(customer.getEmail());
		int minLength =8;
		int maxLength=10;
		if(local!=null) {
			System.out.println("User with email already exists");
			throw new CustomerFoundException();
		}
		else if(customer.getPassword().length()<minLength || customer.getPassword().length()>maxLength) {
			System.out.println("Password doesn't match the criteria");
			throw new PasswordMismatchException();
		}
			else {
		local = this.customerRepository.save(customer);
	}
		return local;
		}

	//Method to update customer
	@Override
	public Customer updateCustomer(Customer customer) {
		return this.customerRepository.save(customer);
	}

	//Method to Get all customers
	@Override
	public Set<Customer> getCustomers() {
		
		return new HashSet<>(this.customerRepository.findAll());
	}

	//Method to get customer by Id
	@Override
	public Customer getCustomer(Long customerId) throws Exception {

		try {
			Customer local = this.customerRepository.findById(customerId).get();
			return local;
		}catch(Exception e) {
			System.out.println("Customer with id not found");
			throw new CustomerNotFoundException();
		}
	}

	//Method to delete customer
	@Override
	public void deleteCustomer(Long customerId) {
		// TODO Auto-generated method stub
		this.customerRepository.deleteById(customerId);
		
	}

}
