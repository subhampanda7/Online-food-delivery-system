package com.masai.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.CustomerException;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.repository.CustomerDao;
import com.masai.repository.SessionDao;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private SessionDao sessionDao;
	
	
	//Verified fully
	@Override
	public Customer addCustomer(Customer customer) throws CustomerException {
		
		Customer existingCustomer = customerDao.findByMobileNumber(customer.getMobileNumber());
		
		if(existingCustomer != null) {
			throw new CustomerException("Customer Already registered with mobile number " + customer.getMobileNumber());
		}
		return customerDao.save(customer);
		
	}

	
	//Verified fully
	@Override
	public Customer updateCustomer(Customer customer, String key) throws CustomerException {

		CurrentUserSession loggedInUser= sessionDao.findByUuid(key);
		
		if(loggedInUser == null) {
			
			throw new CustomerException("Please provide a valid key to update a customer");
		}
		
		if(customer.getCustomerId() == loggedInUser.getUserId()) {
			
			return customerDao.save(customer);
		}
		else
			throw new CustomerException("Invalid Customer Details, please login first");

		
	}

	
	//Verified fully
	@Override
	public Customer removeCustomer(Integer customerId, String key) throws CustomerException {
		
		CurrentUserSession loggedInUser= sessionDao.findByUuid(key);
		
		if(loggedInUser == null) {
			
			throw new CustomerException("Please provide a valid key to update a customer");
		}

		Customer customer = customerDao.findByCustomerId(customerId);
		if(customer == null) {
			throw new CustomerException("No customer exist with id "+customerId);
		}
		
		
		if(customer.getCustomerId() == loggedInUser.getUserId()) {
			
			customerDao.delete(customer);
			sessionDao.delete(loggedInUser);
			return customer;
		}else
			throw new CustomerException("Customer doesn't exist with customer Id: " + customerId);
		
	}

	//Verified fully
	@Override
	public Customer viewCustomer(Integer customerId) throws CustomerException {
		
		Optional<Customer> foundCustomer = customerDao.findById(customerId);
		
		if(foundCustomer.isPresent()) {
			
			return foundCustomer.get();
			
		} else {
			throw new CustomerException("Customer doesn't exist with customer Id: "+ customerId);
		}
		
	}


//	@Override
//	public List<Customer> viewAllCustomers(Restaurant restaurant) throws CustomerException, RestaurantException {
//		// TODO Auto-generated method stub
//		return null;
//	}

	

}
