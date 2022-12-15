package com.masai.service;

import com.masai.model.Category;
import com.masai.model.FoodCart;
import com.masai.model.Item;

public interface CartService {
	
	public FoodCart addCategory(Category cat);
	
	public FoodCart increaseQuantity(FoodCart cart,Item item,Integer quantity);
	
	public FoodCart reduceQuantity(FoodCart cart,Item item,Integer quantiaty);
	
	public FoodCart removeItem(FoodCart cart,Item item);
	
	public FoodCart clearCart(FoodCart cart);
	
	
}
