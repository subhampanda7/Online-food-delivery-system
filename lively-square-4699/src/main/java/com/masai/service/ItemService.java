package com.masai.service;

import java.util.List;

import com.masai.dto.ItemDTO;
import com.masai.exceptions.CategoryException;
import com.masai.exceptions.ItemException;
import com.masai.exceptions.RestaurantException;
import com.masai.model.Item;

public interface ItemService {
	
	//Verified
	public Item addItem(ItemDTO itemdto, String key) throws ItemException, RestaurantException;
	
	//Verified
	public Item updateItem(ItemDTO itemdto, String key) throws ItemException, RestaurantException;
	
	//Verified
	public List<ItemDTO> viewAllItems(String key) throws ItemException,RestaurantException;
	
	//Verified
	public Item removeItem(Integer itemId, String key) throws ItemException, RestaurantException;

}
