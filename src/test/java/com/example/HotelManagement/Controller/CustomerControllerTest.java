package com.example.HotelManagement.Controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.MediaType;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.HotelManagement.entity.Customer;
import com.example.HotelManagement.service.Impl.CustomerServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

	@MockBean
	CustomerServiceImpl customerService;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	void testAddCustomer() throws Exception {
		Customer customer = new Customer("gagana","prakasha","gagana@gmail.com","password");
		when(customerService.addCustomer(customer)).thenReturn(customer);
		mockMvc.perform(post("/api/customer")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(customer)))
				.andExpect(status().isCreated());
		
	}

	@Test
	void testGetCustomer() throws Exception {
		Customer customer = new Customer(100);
		when(customerService.getCustomer(100L)).thenReturn(customer);
		mockMvc.perform(get("/api/customer/{customerId}",100L)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(100));
		
	}

	@Test
	void testGetCustomers() throws Exception {
		Customer customer = new Customer("gagana","prakasha","gagana@gmail.com","password");
		Customer customer2 = new Customer("anusha","T","anusha@gmail.com","password");
		Customer customer3 = new Customer("santhosh","kumar","santhosh@gmail.com","password");
		List<Customer> customers = new ArrayList<>();
		customers.add(customer);
		customers.add(customer2);
		customers.add(customer3);
		Set<Customer> hashcustomers = new HashSet<>();
	    hashcustomers.addAll(customers);
		when(customerService.getCustomers()).thenReturn(hashcustomers);
		mockMvc.perform(get("/")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
//				.andExpect(jsonPath("$[0].firstName").value("gagana"));
			
	}

	@Test
	void testUpdateCustomer() throws JsonProcessingException, Exception {
		Customer customer = new Customer("gagana","prakasha","gagana@gmail.com","password");
		when(customerService.updateCustomer(customer)).thenReturn(customer);
		mockMvc.perform(put("/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(customer)))
				.andExpect(status().isOk());
		
	}

	@Test
	void testDeleteCustomer() throws JsonProcessingException, Exception {
		Customer customer = new Customer(100);
		doNothing().when(customerService).deleteCustomer(100L);
		mockMvc.perform(delete("/{customerId}",100L)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(customer)))
				.andExpect(status().isOk());
	}

}
