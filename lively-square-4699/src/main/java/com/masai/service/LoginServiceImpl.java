package com.masai.service;

import java.time.LocalDateTime;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.LoginException;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.LoginDTO;
import com.masai.model.Restaurant;
import com.masai.repository.CustomerDao;
import com.masai.repository.RestaurantDao;
import com.masai.repository.SessionDao;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private SessionDao sessionDao;
	
	@Autowired
	private RestaurantDao restaurantDao;

	
	//Verified fully
	@Override
	public String loginIntoAccount(LoginDTO loginDto) throws LoginException {
		
		if(loginDto.getRole().equalsIgnoreCase("customer")) {
			
			//Customer Login
			
			Customer existingCustomer = customerDao.findByMobileNumber(loginDto.getMobileNo());
			
			if(existingCustomer == null) {
				
				throw new LoginException("Please Enter a valid mobile number");
			}
			
			Optional<CurrentUserSession> validCustomerSessionOpt =  sessionDao.findById(existingCustomer.getCustomerId());
			
			if(validCustomerSessionOpt.isPresent()) {
				
				throw new LoginException("User already logged in with this mobile number");
			}
			
			if(existingCustomer.getPassword().equals(loginDto.getPassword())) {
				
				String key = RandomString.make(6);
				
				CurrentUserSession currentUserSession = new CurrentUserSession(existingCustomer.getCustomerId(),loginDto.getRole(),key,LocalDateTime.now());
				
				sessionDao.save(currentUserSession);
				
				return currentUserSession.toString();
				
			} else
				throw new LoginException("Please enter valid passowrd");
			
		} else if(loginDto.getRole().equalsIgnoreCase("restaurant")) {
			
			//Restaurant Login 
			
			Restaurant existingRestaurant= restaurantDao.findByContactNumber(loginDto.getMobileNo());
			
			if(existingRestaurant == null) {
				
				throw new LoginException("Please enter a valid mobile number");
				
			}
			
			Optional<CurrentUserSession> validRestaurantSessionOpt =  sessionDao.findById(existingRestaurant.getRestaurantId());
			
			if(validRestaurantSessionOpt.isPresent()) {
				
				throw new LoginException("Restaurant already Logged In with this number");
				
			}
			
			if(existingRestaurant.getPassword().equals(loginDto.getPassword())) {
				
				String key= RandomString.make(6);
			
				CurrentUserSession currentUserSession = new CurrentUserSession(existingRestaurant.getRestaurantId(),loginDto.getRole(),key,LocalDateTime.now());
				
				sessionDao.save(currentUserSession);
	
				return currentUserSession.toString();
			}
			else
				throw new LoginException("Please enter a valid password");
			
			
			
		} else {
			
			throw new LoginException("Please Enter a valid role");
		}
		
		
		
		
	}

	
	//Verified
	@Override
	public String logoutFromAccount(String key) throws LoginException {
		
		CurrentUserSession validCustomerSession = sessionDao.findByUuid(key);
		
		if(validCustomerSession == null) {
			
			throw new LoginException("User Not Logged In with this key "+ key);
			
		}
		sessionDao.delete(validCustomerSession);
		
		return "Logged out successfully";
		
	}

}
