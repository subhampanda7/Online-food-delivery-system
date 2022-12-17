package com.masai.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.dto.RestaurantDTO;
import com.masai.exceptions.RestaurantException;
import com.masai.model.CurrentUserSession;
import com.masai.model.Restaurant;
import com.masai.repository.ItemDao;
import com.masai.repository.RestaurantDao;
import com.masai.repository.SessionDao;

@Service
public class RestaurantServiceImpl implements RestaurantService{
	
	@Autowired
	private RestaurantDao restaurantDao;
	
	@Autowired
	private ItemDao itemDao;
	
	@Autowired
	private SessionDao sessionDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//Verified fully
	@Override
	public Restaurant addRestaurant(RestaurantDTO resdto) throws RestaurantException {
		
		
		Restaurant restaurant = restaurantDao.findByContactNumber(resdto.getContactNumber());
		
		
			
			if(restaurant == null) {
				
				Restaurant rest  = this.modelMapper.map(resdto, Restaurant.class);
				
				return restaurantDao.save(rest);
				
			}else {
				
				throw new RestaurantException("Mobile already exists..");
				
			}
		
	}

	
	//verified fully
	@Override
	public Restaurant updateRestaurant(RestaurantDTO resdto, String key) throws RestaurantException {
		
		
		CurrentUserSession curr = sessionDao.findByUuid(key);
		
		if(curr == null) throw new RestaurantException("No Restaurant Logged in with this key");
		
		if(curr.getRole().equalsIgnoreCase("customer")) throw new RestaurantException("You are not authorized");
		
		if(resdto.getRestaurantId() == curr.getUserId()) {
			
			Restaurant restaurant = restaurantDao.findById(resdto.getRestaurantId())
				.orElseThrow(() -> new RestaurantException("No such restaurant exists..."));
			
			restaurant = this.modelMapper.map(resdto, Restaurant.class);
			
			return restaurantDao.save(restaurant);
		}else {
			
			throw new RestaurantException("Enter Authorised Restaurant Id");
			
		}
		
	}

	//verified fully
	@Override
	public Restaurant removeRestaurant(Integer id, String key) throws RestaurantException {
		
		CurrentUserSession curr = sessionDao.findByUuid(key);
		
		if(curr == null) throw new RestaurantException("No Restaurant Logged in with this key");
		
		if(curr.getRole().equalsIgnoreCase("customer")) throw new RestaurantException("You are not authorized");
		
		if(id == curr.getUserId()) {
			Restaurant restaurant = restaurantDao.findById(id)
				.orElseThrow(() -> new RestaurantException("No such restaurant exists"));
			
			restaurantDao.delete(restaurant);
		
			return restaurant;
		
		}else {
			
			throw new RestaurantException("Enter authorised restaurant id");
			
		}
		
	}

	//verified fully
	@Override
	public RestaurantDTO viewRestaurant(Integer id) throws RestaurantException {
		
		Restaurant restaurant = restaurantDao.findById(id)
				.orElseThrow(() -> new RestaurantException("No such restaurant exists..."));
			
		return this.modelMapper.map(restaurant, RestaurantDTO.class);
		
	}
	
	
	
	
	
}
