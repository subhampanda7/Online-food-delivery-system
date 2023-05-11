package com.masai.service;

import com.masai.exceptions.CartException;
import com.masai.exceptions.ItemException;
import com.masai.exceptions.RestaurantException;
import com.masai.model.FoodCart;

public interface CartService {
	
	//Verified
	public FoodCart addItemToCart(Integer itemId, String key) throws RestaurantException, ItemException;
	
	//Verified
	public FoodCart removeItem(Integer itemId, String key) throws RestaurantException, ItemException, CartException;

}
