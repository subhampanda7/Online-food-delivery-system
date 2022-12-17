package com.masai.service;

import com.masai.dto.RestaurantDTO;
import com.masai.exceptions.RestaurantException;
import com.masai.model.Restaurant;

public interface RestaurantService {
	
	public Restaurant addRestaurant(RestaurantDTO resdto) throws RestaurantException;
	
	public Restaurant updateRestaurant(RestaurantDTO resdto, String key) throws RestaurantException;
	
	public Restaurant removeRestaurant(Integer id, String key) throws RestaurantException;
	
	public RestaurantDTO viewRestaurant(Integer id) throws RestaurantException;
	
}
