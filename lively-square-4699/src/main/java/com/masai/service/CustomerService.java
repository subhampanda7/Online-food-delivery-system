package com.masai.service;

import org.springframework.stereotype.Repository;

import com.masai.dto.CustAddDto;
import com.masai.dto.CustomerDto;
import com.masai.exceptions.AddressException;
import com.masai.exceptions.CustomerException;
import com.masai.model.Address;
import com.masai.model.Customer;

@Repository
public interface CustomerService {
	
	//verified
	public Customer registerCustomer(CustAddDto customer) throws CustomerException;
	
	//verified
	public Customer updateCustomer(CustAddDto customer, String key) throws CustomerException;
	
	//verified
	public String deleteCustomer(Integer customerId, String key) throws CustomerException;
	
	//verified
	public CustomerDto addAddress(Integer customerId, Address add, String key) throws CustomerException, AddressException;
}
