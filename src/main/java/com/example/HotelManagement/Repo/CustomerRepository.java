package com.example.HotelManagement.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.HotelManagement.entity.Customer;



public interface CustomerRepository extends JpaRepository<Customer,Long> {

	//To fetch customer by email
	Customer findByEmail(String email);

}
