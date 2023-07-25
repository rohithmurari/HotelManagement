package com.example.HotelManagement.service;

import java.util.Set;
import com.example.HotelManagement.entity.Customer;

public interface CustomerService {

	public Customer addCustomer(Customer customer) throws Exception;
	public Customer updateCustomer(Customer customer);
	public Set<Customer> getCustomers();
	public Customer getCustomer(Long customerId) throws Exception;
	public void deleteCustomer(Long customerId);
}
