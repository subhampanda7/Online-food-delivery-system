package com.masai.service;

import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.dto.CustAddDto;
import com.masai.dto.CustomerDto;
import com.masai.exceptions.AddressException;
import com.masai.exceptions.CustomerException;
import com.masai.model.Address;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.FoodCart;
import com.masai.repository.AddressRepo;
import com.masai.repository.CustomerRepo;
import com.masai.repository.RestaurantRepo;
import com.masai.repository.SessionRepo;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepo cr;

	@Autowired
	private AddressRepo ar;

	@Autowired
	private RestaurantRepo rr;

	@Autowired
	private SessionRepo sessionrepo;

	@Autowired
	private ModelMapper modelMapper;

	
	@Override
	public Customer registerCustomer(CustAddDto customer) throws CustomerException {

		Customer cust = cr.findByEmail(customer.getEmail());

		if (cust == null) {

			cust = cr.findByMobileNumber(customer.getMobileNumber());

			if (cust == null) {

				cust = this.modelMapper.map(customer, Customer.class);

				FoodCart fc = new FoodCart();

				fc.setCustomer(cust);

				cust.setFoodCart(fc);

				return cr.save(cust);

			} else {
				throw new CustomerException("Mobile already exists");

			}

		} else {
			throw new CustomerException("Email already exists");

		}

	}

	
	@Override
	public Customer updateCustomer(CustAddDto customer, String key) throws CustomerException {

		CurrentUserSession curr = sessionrepo.findByUuid(key);

		if (curr == null)
			throw new CustomerException("No customer Logged in with this key");

		if (curr.getRole().equalsIgnoreCase("restaurant"))
			throw new CustomerException("Log in as a customer");

		if (curr.getUserId() == customer.getCustomerId()) {

			Customer c = cr.findById(customer.getCustomerId())
					.orElseThrow(() -> new CustomerException("You are not authorized"));

			Customer cust = this.modelMapper.map(customer, Customer.class);

			if (customer.getPassword() == null || customer.getPassword().equals(""))
				cust.setPassword(c.getPassword());

			cust.setFoodCart(c.getFoodCart());

			cust.setOrders(c.getOrders());

			cust.setAddresses(c.getAddresses());

			return cr.save(cust);

		} else {

			throw new CustomerException("You are not authorized");

		}

	}

	
	@Override
	public String deleteCustomer(Integer customerId, String key) throws CustomerException {

		CurrentUserSession curr = sessionrepo.findByUuid(key);

		if (curr == null)
			throw new CustomerException("No customer Logged in with this key");

		if (curr.getRole().equalsIgnoreCase("restaurant"))
			throw new CustomerException("Log in as a customer");

		if (curr.getUserId() == customerId) {

			Customer cust = cr.findById(customerId)
					.orElseThrow(() -> new CustomerException(""));

			cr.delete(cust);

			return "Customer Successfully Deleted";

		} else {
			throw new CustomerException("You are not authorized");
		}

	}

	
	@Override
	public CustomerDto addAddress(Integer customerId, Address add, String key)
			throws CustomerException, AddressException {

		CurrentUserSession curr = sessionrepo.findByUuid(key);

		if (curr == null)
			throw new CustomerException("No customer Logged in with this key");

		if (curr.getRole().equalsIgnoreCase("restaurant"))
			throw new CustomerException("Log in as a customer");

		if (curr.getUserId() == customerId) {

			Customer cust = cr.findById(customerId)
					.orElseThrow(() -> new CustomerException(""));

			Set<Address> addes = cust.getAddresses();

			Address address = ar.findByStreetPincodeBuilding(add.getStreet(), add.getPincode(), add.getBuilding());

			if (address != null) {

				throw new AddressException("Address already added");

			}

			addes.add(add);

			cust.setAddresses(addes);

			cust = cr.save(cust);

			return this.modelMapper.map(cust, CustomerDto.class);

		} else {
			throw new CustomerException("You are not authorized");
		}

	}
	
}
