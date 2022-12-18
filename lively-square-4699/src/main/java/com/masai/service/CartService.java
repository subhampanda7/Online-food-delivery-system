package com.masai.service;

import com.masai.exceptions.CartException;
import com.masai.exceptions.ItemException;
import com.masai.exceptions.RestaurantException;
import com.masai.model.FoodCart;

public interface CartService {
	
	public FoodCart addItemToCart(Integer itemId, String key) throws RestaurantException, ItemException;

	public FoodCart increaseQuantity(Integer itemId, Integer quantity, String key) throws RestaurantException, ItemException, CartException;

	public FoodCart reduceQuantity(Integer itemId, Integer quantity, String key) throws RestaurantException, ItemException, CartException;

	public FoodCart removeItem(Integer itemId, String key) throws RestaurantException, ItemException, CartException;

	public String clearCart(String key) throws RestaurantException, CartException;
	
	

}
