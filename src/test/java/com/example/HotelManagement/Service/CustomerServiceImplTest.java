package com.example.HotelManagement.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.HotelManagement.Helper.CustomerFoundException;
import com.example.HotelManagement.Helper.PasswordMismatchException;
import com.example.HotelManagement.Repo.CustomerRepository;
import com.example.HotelManagement.entity.Customer;
import com.example.HotelManagement.service.Impl.CustomerServiceImpl;

@SpringBootTest
class CustomerServiceImplTest {

	@MockBean
	CustomerRepository customerRepository;
	
	@Autowired
	CustomerServiceImpl customerService;
	
	@Test
	void testAddCustomer() throws Exception {
		Customer customer = new Customer("gagana","prakasha","gagana@gmail.com","password");
		when(customerRepository.save(customer)).thenReturn(customer);
		assertEquals(customer, customerService.addCustomer(customer));
	}
	
	@Test
	void testAddCustomerWithPassword() throws Exception {
		Customer customer = new Customer("gagana","prakasha","gagana@gmail.com","passwordyttryry");
		when(customerRepository.save(customer)).thenReturn(customer);
		
		PasswordMismatchException passwordMismatchException = assertThrows(PasswordMismatchException.class,
				() -> customerService.addCustomer(customer));
		assertEquals("Password doesn't match the criteria", passwordMismatchException.getMessage());
		
	}
	
//	@Test
//	void testAddCustomerWithEmail() throws Exception {
//		
//		Customer customer = new Customer("gagana","prakasha","gagana@gmail.com","password");
//		Customer customer1 = new Customer("anusha","t","gagana@gmail.com","password");
////		when(customerRepository.save(customer)).thenReturn(customer);
//		customerService.addCustomer(customer);
//		
//		CustomerFoundException customerFoundException = assertThrows(CustomerFoundException.class,
//				() -> customerService.addCustomer(customer1));
//		assertEquals("Customer with this email already exists", customerFoundException.getMessage());
//		
//	}
//	
	@Test
	void testUpdateCustomer() {
		Customer customer = new Customer("gagana","prakasha","gagana@gmail.com","password");
		when(customerRepository.save(customer)).thenReturn(customer);
		assertEquals(customer, customerService.updateCustomer(customer));
	}

	@Test
	void testGetCustomers() {
		Customer customer = new Customer("gagana","prakasha","gagana@gmail.com","password");
		Customer customer2 = new Customer("anusha","T","anusha@gmail.com","password");
		Customer customer3 = new Customer("santhosh","kumar","santhosh@gmail.com","password");
		List<Customer> customers = new ArrayList<>();
		customers.add(customer);
		customers.add(customer2);
		customers.add(customer3);
		when(customerRepository.findAll()).thenReturn(customers);
		
		Set<Customer> hashcustomers = new HashSet<>();
	    hashcustomers.addAll(customers);
	        
		assertEquals(hashcustomers,customerService.getCustomers());
	}

	@Test
	void testGetCustomer() throws Exception {
		Customer customer = new Customer(100);
		when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
		assertEquals(customer,customerService.getCustomer(100L));
		
	}

	@Test
	void testDeleteCustomer() {
		Customer customer = new Customer("gagana","prakasha","gagana@gmail.com","password");
		customerService.deleteCustomer(customer.getId());
		verify(customerRepository,times(1)).deleteById(customer.getId());
	}

}
