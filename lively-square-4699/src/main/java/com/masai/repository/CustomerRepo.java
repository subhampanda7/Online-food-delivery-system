package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer>{

	public Customer findByEmail(String email);
	
	public Customer findByMobileNumber(String number);
	
}
