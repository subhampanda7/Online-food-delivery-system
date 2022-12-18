package com.masai.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.dto.RestaurantAddDTO;
import com.masai.dto.RestaurantDTO;
import com.masai.exceptions.RestaurantException;
import com.masai.model.CurrentUserSession;
import com.masai.model.Restaurant;
import com.masai.repository.AddressRepo;
import com.masai.repository.ItemRepo;
import com.masai.repository.RestaurantRepo;
import com.masai.repository.SessionRepo;

@Service
public class RestaurantServiceImpl implements RestaurantService{
	
	@Autowired
	private AddressRepo ar;
	
	@Autowired
	private RestaurantRepo rr;
	
	@Autowired
	private ItemRepo ir;
	
	@Autowired
	private SessionRepo sessionrepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Restaurant addRestaurant(RestaurantAddDTO resDto) throws RestaurantException {
		
		Restaurant restaurant = rr.findByContactNumber(resDto.getContactNumber());
		
		if(restaurant == null) {
			
			restaurant = rr.findByEmail(resDto.getEmail());
			
			if(restaurant == null) {
				
				Restaurant rest  = this.modelMapper.map(resDto, Restaurant.class);
				
				return rr.save(rest);
				
			}else {
				
				throw new RestaurantException("Email already exists");
				
			}
			
		}else {
			
			throw new RestaurantException("Mobile already exists");
			
		}
			
		
	}

	
	@Override
	public Restaurant updateRestaurant(RestaurantAddDTO resDto, String key) throws RestaurantException {
		
		CurrentUserSession curr = sessionrepo.findByUuid(key);
		
		if(curr == null) throw new RestaurantException("No Restaurant Logged in with this key");
		
		if(curr.getRole().equalsIgnoreCase("customer")) throw new RestaurantException("You are not authorized");
		
		if(resDto.getRestaurantId() == curr.getUserId()) {
			Restaurant restaurant = rr.findById(resDto.getRestaurantId())
				.orElseThrow(() -> new RestaurantException("No such restaurant exists"));
			
			restaurant = this.modelMapper.map(resDto, Restaurant.class);
			
			return rr.save(restaurant);
		}else {
			
			throw new RestaurantException("Enter Authorised Restaurant Id..");
			
		}
		
	}

	
	@Override
	public Restaurant removeRestaurant(Integer resId, String key) throws RestaurantException {
		
		CurrentUserSession curr = sessionrepo.findByUuid(key);
		
		if(curr == null) throw new RestaurantException("No Restaurant Logged in with this key");
		
		if(curr.getRole().equalsIgnoreCase("customer")) throw new RestaurantException("You are not authorized");
		
		if(resId == curr.getUserId()) {
			Restaurant restaurant = rr.findById(resId)
				.orElseThrow(() -> new RestaurantException("No such restaurant exists"));
			
			rr.delete(restaurant);
		
			return restaurant;
		
		}else {
			
			throw new RestaurantException("Enter Authorised Restaurant Id");
			
		}

	}

	
	@Override
	public RestaurantDTO viewRestaurant(Integer resId) throws RestaurantException {
		
		Restaurant restaurant = rr.findById(resId)
				.orElseThrow(() -> new RestaurantException("No such restaurant exists"));
			
		return this.modelMapper.map(restaurant, RestaurantDTO.class);
			
	}
	
	
}
