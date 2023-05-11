package com.masai.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.dto.LoginDTO;
import com.masai.exceptions.LoginException;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.Restaurant;
import com.masai.repository.CustomerRepo;
import com.masai.repository.RestaurantRepo;
import com.masai.repository.SessionRepo;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private CustomerRepo cr;
	
	@Autowired
	private SessionRepo sr;
	
	@Autowired
	private RestaurantRepo rr;
	
	@Override
	public String logIntoAccount(LoginDTO dto)throws LoginException{
		
		//Customer
		if(dto.getRole().equalsIgnoreCase("customer")) {
			
			Customer existingCustomer= cr.findByMobileNumber(dto.getMobileNo());
			
			if(existingCustomer == null) {
				
				throw new LoginException("Please Enter a valid mobile number");
				
			}
			
			Optional<CurrentUserSession> validCustomerSessionOpt =  sr.findById(existingCustomer.getCustomerId());
			
			if(validCustomerSessionOpt.isPresent()) {
				
				throw new LoginException("User already Logged In with this number");
				
			}
			
			if(existingCustomer.getPassword().equals(dto.getPassword())) {
				
				String key= RandomString.make(4);
			
				CurrentUserSession currentUserSession = new CurrentUserSession(existingCustomer.getCustomerId(),dto.getRole(),key,LocalDateTime.now());
				
				sr.save(currentUserSession);
	
				return currentUserSession.toString();
			}
			else
				throw new LoginException("Please Enter a valid password");
			
		}else if(dto.getRole().equalsIgnoreCase("restaurant")){
			
			//Restaurant
			Restaurant existingRestaurant= rr.findByContactNumber(dto.getMobileNo());
			
			if(existingRestaurant == null) {
				
				throw new LoginException("Please Enter a valid mobile number");
				
			}
			
			Optional<CurrentUserSession> validCustomerSessionOpt =  sr.findById(existingRestaurant.getRestaurantId());
			
			if(validCustomerSessionOpt.isPresent()) {
				
				throw new LoginException("Restaurant already Logged In with this number");
				
			}
			
			if(existingRestaurant.getPassword().equals(dto.getPassword())) {
				
				String key= RandomString.make(4);
			
				CurrentUserSession currentUserSession = new CurrentUserSession(existingRestaurant.getRestaurantId(),dto.getRole(),key,LocalDateTime.now());
				
				sr.save(currentUserSession);
	
				return currentUserSession.toString();
			}
			else
				throw new LoginException("Please Enter a valid password");
			
		}else {
			
			throw new LoginException("Please Enter a valid role");
			
		}
		
		
	}


	@Override
	public String logOutFromAccount(String key)throws LoginException {
		
		CurrentUserSession validCustomerSession = sr.findByUuid(key);
		
		if(validCustomerSession == null) {
			throw new LoginException("User Not Logged In with this number");
			
		}
		
		sr.delete(validCustomerSession);
		
		
		return "Logged Out !";
		
		
	}

}
