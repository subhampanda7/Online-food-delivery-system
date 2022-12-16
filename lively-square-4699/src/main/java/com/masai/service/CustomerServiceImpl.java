package com.masai.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.CustomerException;
import com.masai.model.Customer;
import com.masai.repository.CustomerDao;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerDao customerDao;

	@Override
	public Customer addCustomer(Customer customer) throws CustomerException {

		Customer savedCustomer = customerDao.save(customer);
		return savedCustomer;
		
	}

	@Override
	public Customer updateCustomer(Customer customer) throws CustomerException {

//		Optional<Customer> opt = customerDao.findById(customer.getCustomerId());
		Customer opt = customerDao.findByCustomerId(customer.getCustomerId());
		
		if(opt == null) throw new CustomerException("Invalid customer details..");
		else {
			
			Customer updatedCustomer = customerDao.save(customer);
			return updatedCustomer;
			
		}
		
	}

	@Override
	public Customer removeCustomer(String customerId) throws CustomerException {
		
		Customer opt = customerDao.findByCustomerId(customerId);
		
		if(opt != null) {
			
//			Customer customerToBeDeleted = opt.get();
//			customerDao.delete(customerToBeDeleted);
//			return customerToBeDeleted;
			customerDao.delete(opt);
			return opt;
		} else {
			
			throw new CustomerException("Customer doesn't exist with customer Id: "+customerId);
			
		}
		
		
	}

	@Override
	public Customer viewCustomer(String email) throws CustomerException {
		
		List<Customer> foundCustomer = customerDao.findByEmail(email);
		
		if(foundCustomer != null) {
			return foundCustomer.get(0);
			
		} else {
			throw new CustomerException("Customer doesn't exist with customer Id: "+ email);
		}
		
		
	}

//	@Override
//	public List<Customer> viewAllCustomers(Restaurant restaurant) throws CustomerException, RestaurantException {
//		// TODO Auto-generated method stub
//		return null;
//	}

	

}
