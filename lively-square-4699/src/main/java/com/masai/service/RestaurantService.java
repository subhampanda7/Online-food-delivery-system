package com.masai.service;

import com.masai.dto.RestaurantAddDTO;
import com.masai.dto.RestaurantDTO;
import com.masai.exceptions.RestaurantException;
import com.masai.model.Restaurant;

public interface RestaurantService {
	
	//Verified
	public Restaurant addRestaurant(RestaurantAddDTO res) throws RestaurantException;
	
	//Verified
	public Restaurant updateRestaurant(RestaurantAddDTO res, String key) throws RestaurantException;
	
	//Verified
	public Restaurant removeRestaurant(Integer resId, String key) throws RestaurantException;
	
	//Verified
	public RestaurantDTO viewRestaurant(Integer resId) throws RestaurantException;
	
}
