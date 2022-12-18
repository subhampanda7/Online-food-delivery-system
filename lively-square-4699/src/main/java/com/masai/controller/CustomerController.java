package com.masai.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.dto.CustAddDto;
import com.masai.dto.CustomerDto;
import com.masai.dto.RestaurantDTO;
import com.masai.exceptions.AddressException;
import com.masai.exceptions.CartException;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.ItemException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.RestaurantException;
import com.masai.model.Address;
import com.masai.model.Customer;
import com.masai.model.FoodCart;
import com.masai.service.CartService;
import com.masai.service.CustomerService;
import com.masai.service.ItemService;
import com.masai.service.LoginService;
import com.masai.service.RestaurantService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService cs;

	@Autowired
	private CartService carts;

	@Autowired
	private ItemService its;

	@Autowired
	private RestaurantService rs;

	@Autowired
	private LoginService customerLogin;

	
	@PostMapping("/register") 
	public ResponseEntity<Customer> registerCustomerHandler(@Valid @RequestBody CustAddDto customer) throws CustomerException {

		Customer cust = cs.registerCustomer(customer);

		return new ResponseEntity<Customer>(cust, HttpStatus.CREATED);

	}

	@PutMapping("/update") 
	public ResponseEntity<Customer> updateCustomerHandler(@Valid @RequestBody CustAddDto customer, String key)
			throws CustomerException {

		Customer cust = cs.updateCustomer(customer, key);

		return new ResponseEntity<Customer>(cust, HttpStatus.CREATED);

	}

	@DeleteMapping("/delete") 
	public ResponseEntity<String> deleteCustomer(@RequestParam Integer customerId, @RequestParam String key)
			throws CustomerException, LoginException {

		String cust = cs.deleteCustomer(customerId, key);
		customerLogin.logOutFromAccount(key);

		return new ResponseEntity<String>(cust, HttpStatus.ACCEPTED);

	}

	
	@PostMapping("/address/add") 
	public ResponseEntity<CustomerDto> addAddress(@RequestParam Integer customerId, @RequestBody Address add,
			@RequestParam String key) throws CustomerException, AddressException {
		CustomerDto cdto = cs.addAddress(customerId, add, key);

		return new ResponseEntity<CustomerDto>(cdto, HttpStatus.ACCEPTED);

	}

	
	@PutMapping("/cart/add") 
	public ResponseEntity<FoodCart> addItemToCartHandler(@RequestParam Integer itemId, @RequestParam String key)
			throws RestaurantException, ItemException {

		FoodCart fc = carts.addItemToCart(itemId, key);

		return new ResponseEntity<FoodCart>(fc, HttpStatus.ACCEPTED);

	}

	@DeleteMapping("/cart/remove") 
	public ResponseEntity<FoodCart> removeHandler(@RequestParam Integer itemId, @RequestParam String key)
			throws RestaurantException, ItemException, CartException {

		FoodCart fc = carts.removeItem(itemId, key);

		return new ResponseEntity<FoodCart>(fc, HttpStatus.OK);
	}

	
	
	@GetMapping("/search/restaurant") 
	public ResponseEntity<RestaurantDTO> viewRestaurant(@RequestParam Integer resId) throws RestaurantException {

		RestaurantDTO rdto = rs.viewRestaurant(resId);

		return new ResponseEntity<RestaurantDTO>(rdto, HttpStatus.OK);
	}

	

}
