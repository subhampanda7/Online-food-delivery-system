package com.masai.controllers;

import javax.validation.Valid;

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

import com.masai.exceptions.CustomerException;
import com.masai.model.Customer;
import com.masai.service.CustomerService;


@RestController
//@RequestMapping("/customerservice")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/customer")
	public ResponseEntity<Customer> addCustomerHandler(@Valid @RequestBody Customer customer)throws CustomerException {

		Customer savedCustomer = customerService.addCustomer(customer);
		return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/customer")
	public ResponseEntity<Customer> updateCustomerHandler(@Valid @RequestBody Customer customer)throws CustomerException {
		
		Customer updatedCustomer = customerService.addCustomer(customer);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		
	}

	@DeleteMapping("/customer/{id}")
	public ResponseEntity<Customer> removeCustomerHandler(@PathVariable("id") String customerId) throws CustomerException {
		
		Customer deletedCustomer = customerService.removeCustomer(customerId);
		return new ResponseEntity<Customer>(deletedCustomer, HttpStatus.OK);
		
	}
	
	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer> viewCustomer(@PathVariable("id") String customerId)throws CustomerException {
		
		Customer viewedcustomer = customerService.viewCustomer(customerId);
		return new ResponseEntity<Customer>(viewedcustomer, HttpStatus.FOUND);
		
	}
	
	
}
