package com.masai.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.dto.ItemDTO;
import com.masai.dto.RestaurantDTO;
import com.masai.exceptions.ItemException;
import com.masai.exceptions.RestaurantException;
import com.masai.model.CurrentUserSession;
import com.masai.model.Item;
import com.masai.model.Restaurant;
import com.masai.repository.CategoryDao;
import com.masai.repository.ItemDao;
import com.masai.repository.RestaurantDao;
import com.masai.repository.SessionDao;

@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private ItemDao itemDao;

	@Autowired
	private RestaurantDao restaurantDao;

	@Autowired
	private SessionDao sessionDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	
	//Verified fully
	@Override
	public Item addItem(ItemDTO itemdto, String key) throws ItemException, RestaurantException {
		
		CurrentUserSession curr = sessionDao.findByUuid(key);

		if (curr == null)
			throw new RestaurantException("Please enter the correct restaurant key");

		if (curr.getRole().equalsIgnoreCase("customer"))
			throw new RestaurantException("Customer is not authorized to add item");

		Restaurant restaurant = restaurantDao.findById(curr.getUserId())
				.orElseThrow(() -> new RestaurantException("No such restaurant present"));

		List<Item> items = restaurant.getItems();

		Item item = new Item();
		item.setItemName(itemdto.getItemName());
		item.setCost(itemdto.getCost());
		item.setCategory(null);
		item.setRestaurant(restaurant);
			

		for (Item iterator_item : items) {

			if (iterator_item.equals(item))
				throw new ItemException("Item already present");

		}

		items.add(item);

		restaurant.setItems(items);

		return itemDao.save(item);
		
	}

	
	//Verified fully
	@Override
	public Item updateItem(ItemDTO itemdto, String key) throws ItemException, RestaurantException {

		CurrentUserSession curr = sessionDao.findByUuid(key);

		if (curr == null)
			throw new RestaurantException("Please enter the correct restaurant key");

		if (curr.getRole().equalsIgnoreCase("customer"))
			throw new RestaurantException("Customer is not authorized to update item");

		Restaurant restaurant = restaurantDao.findById(curr.getUserId()).orElseThrow(() -> new RestaurantException(""));

		List<Item> items = restaurant.getItems();

		Item item = new Item();
		item.setItemId(itemdto.getItemId());
		item.setItemName(itemdto.getItemName());
		item.setCost(itemdto.getCost());

		for (Item itm : items) {

			if (itm.getItemId() == itemdto.getItemId()) {

				itm.setItemName(itemdto.getItemName());
				itm.setCost(itemdto.getCost());

				return itemDao.save(itm);
			}

		}

		throw new ItemException("No such item is present");

	}

	//Verified fully
	@Override
	public ItemDTO viewItem(Integer itemId) throws ItemException {

		Item item = itemDao.findById(itemId).orElseThrow(() -> new ItemException("No such item present with this id"));

		ItemDTO idto = new ItemDTO();
		idto.setItemId(item.getItemId());
		idto.setItemName(item.getItemName());
		idto.setCost(item.getCost());

		RestaurantDTO rdto = new RestaurantDTO();

		rdto.setRestaurantName(item.getRestaurant().getRestaurantName());
		rdto.setContactNumber(item.getRestaurant().getContactNumber());
		rdto.setAddress(item.getRestaurant().getAddress());

		idto.setRestDTO(rdto);

		return idto;

	}

	//Verified fully
	@Override
	public Item removeItem(Integer itemId, String key) throws ItemException, RestaurantException {

		CurrentUserSession curr = sessionDao.findByUuid(key);

		if (curr == null)
			throw new RestaurantException("Please enter the correct restaurant key");

		if (curr.getRole().equalsIgnoreCase("customer"))
			throw new RestaurantException("Customer is not authorized to update item");

		Restaurant restaurant = restaurantDao.findById(curr.getUserId()).orElseThrow(() -> new RestaurantException("No such item is present"));

		List<Item> items = restaurant.getItems();

		for (Item itm : items) {

			if (itm.getItemId() == itemId) {

				items.remove(itm);

				itemDao.delete(itm);

				restaurantDao.save(restaurant);

				return itm;

			}

		}

		throw new ItemException("No such item is present with this id");

	}

	
	//Verified fully
	@Override
	public List<ItemDTO> viewAllItems(String key) throws ItemException,RestaurantException {
	   
		CurrentUserSession curr = sessionDao.findByUuid(key);

		if (curr == null)
			throw new RestaurantException("Please enter the correct restaurant key");

		if (curr.getRole().equalsIgnoreCase("customer"))
			throw new RestaurantException("Customer is not authorized to update item");

		Restaurant restaurant = restaurantDao.findById(curr.getUserId()).orElseThrow(() -> new RestaurantException(""));
		
		
		List<Item> items = itemDao.findAll();
		
		
		if(items.isEmpty()) throw new ItemException("No item is present");
	    List<ItemDTO> itemsdto = new ArrayList<>();
	    
	    for(Item iterItem : items ) {
	    	ItemDTO idto = new ItemDTO();
	    	idto.setCost(iterItem.getCost());
	    	idto.setItemId(iterItem.getItemId());
	    	idto.setItemName(iterItem.getItemName());
	    	
	    	itemsdto.add(idto);
	    }
	    
	    
	    return itemsdto;
	}
	
//
//	@Override
//	public Item addItemToCategoryByName(Integer itemId, String categoryName, String key)
//			throws ItemException, CategoryException, RestaurantException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<ItemDTO> viewAllItemsByCategory(Integer categoryId) throws CategoryException {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public List<ItemDTO> viewAllItemsByRestaurant(Integer restaurantId) throws RestaurantException {

		Restaurant restaurant = restaurantDao.findById(restaurantId)
				.orElseThrow(() -> new RestaurantException("No such restaurant found with this id"));

		List<Item> items = restaurant.getItems();

		List<ItemDTO> idtos = new ArrayList<>();

		for (Item item : items) {

			ItemDTO idto = new ItemDTO();
			idto.setItemId(item.getItemId());
			idto.setItemName(item.getItemName());
			idto.setCost(item.getCost());

			RestaurantDTO rdto = new RestaurantDTO();

			rdto.setRestaurantName(item.getRestaurant().getRestaurantName());
			rdto.setContactNumber(item.getRestaurant().getContactNumber());
			rdto.setAddress(item.getRestaurant().getAddress());

			idto.setRestDTO(rdto);

			idtos.add(idto);
		}

		return idtos;

	}


	@Override
	public List<ItemDTO> viewAllItemsByName(String name) throws ItemException {

		List<Item> items = itemDao.findByItemNameContaining(name);

		System.out.println(items);

		List<ItemDTO> idtos = new ArrayList<>();

		for (Item item : items) {

			ItemDTO idto = new ItemDTO();
			idto.setItemId(item.getItemId());
			idto.setItemName(item.getItemName());
			idto.setCost(item.getCost());

			RestaurantDTO rdto = new RestaurantDTO();

			rdto.setRestaurantName(item.getRestaurant().getRestaurantName());
			rdto.setContactNumber(item.getRestaurant().getContactNumber());
			rdto.setAddress(item.getRestaurant().getAddress());

			idto.setRestDTO(rdto);

			idtos.add(idto);
		}

		return idtos;
	}
	
	
}
