package com.masai.service;

import java.util.List;

import com.masai.dto.ItemDTO;
import com.masai.exceptions.CategoryException;
import com.masai.exceptions.ItemException;
import com.masai.exceptions.RestaurantException;
import com.masai.model.Item;

public interface ItemService {
	
	public Item addItem(ItemDTO itemdto, String key) throws ItemException, RestaurantException;
	
	public Item updateItem(ItemDTO itemdto, String key) throws ItemException, RestaurantException;
	
	public ItemDTO viewItem(Integer itemId) throws ItemException;
	
	public List<ItemDTO> viewAllItems(String key) throws ItemException,RestaurantException;
	
	public Item removeItem(Integer itemId, String key) throws ItemException, RestaurantException;
	
//	public Item addItemToCategoryByName(Integer itemId, String categoryName, String key) throws ItemException, CategoryException, RestaurantException;
	
//	public List<ItemDTO> viewAllItemsByCategory(Integer categoryId) throws CategoryException;
	
	public List<ItemDTO> viewAllItemsByRestaurant(Integer restaurantId) throws RestaurantException;
	
	public List<ItemDTO> viewAllItemsByName(String name)throws ItemException;

}
