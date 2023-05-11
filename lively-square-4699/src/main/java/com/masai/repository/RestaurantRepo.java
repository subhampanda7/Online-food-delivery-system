package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Address;
import com.masai.model.Restaurant;

public interface RestaurantRepo extends JpaRepository<Restaurant, Integer>{

	public List<Restaurant> findByRestaurantName(String restaurantName);
	
	public Restaurant findByAddress(Address address);
	
	public Restaurant findByContactNumber(String number);
	
	public Restaurant findByEmail(String email);
	
}
