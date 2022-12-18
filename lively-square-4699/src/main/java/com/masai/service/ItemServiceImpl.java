package com.masai.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.dto.ItemDTO;
import com.masai.dto.RestaurantDTO;
import com.masai.exceptions.CategoryException;
import com.masai.exceptions.ItemException;
import com.masai.exceptions.RestaurantException;
import com.masai.model.Category;
import com.masai.model.CurrentUserSession;
import com.masai.model.Item;
import com.masai.model.Restaurant;
import com.masai.repository.CategoryRepo;
import com.masai.repository.ItemRepo;
import com.masai.repository.RestaurantRepo;
import com.masai.repository.SessionRepo;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepo itemRepo;

	@Autowired
	private RestaurantRepo rr;

	@Autowired
	private SessionRepo sessionrepo;

	@Autowired
	private CategoryRepo categoryrepo;

	
	@Override
	public Item addItem(ItemDTO itemdto, String key) throws ItemException, RestaurantException {

		CurrentUserSession curr = sessionrepo.findByUuid(key);

		if (curr == null)
			throw new RestaurantException("No restaurant Logged in with this key");

		if (curr.getRole().equalsIgnoreCase("customer"))
			throw new RestaurantException("You are not authorized");

		Restaurant restaurant = rr.findById(curr.getUserId())
				.orElseThrow(() -> new RestaurantException("Invalid item id"));

		List<Item> items = restaurant.getItemList();

		Item item = new Item();
		item.setItemName(itemdto.getItemName());
		item.setCost(itemdto.getCost());
		item.setCategory(null);
		item.setRestaurant(restaurant);

		for (Item itm : items) {

			if (itm.equals(item))
				throw new ItemException("Item is already Present");

		}

		items.add(item);

		restaurant.setItemList(items);

		return itemRepo.save(item);

	}

	
	@Override
	public Item updateItem(ItemDTO itemdto, String key) throws ItemException, RestaurantException {

		CurrentUserSession curr = sessionrepo.findByUuid(key);

		if (curr == null)
			throw new RestaurantException("No restaurant Logged in with this key");

		if (curr.getRole().equalsIgnoreCase("customer"))
			throw new RestaurantException("You are not authorized");

		Restaurant restaurant = rr.findById(curr.getUserId()).orElseThrow(() -> new RestaurantException(""));

		List<Item> items = restaurant.getItemList();

		Item item = new Item();
		item.setItemId(itemdto.getItemId());
		item.setItemName(itemdto.getItemName());
		item.setCost(itemdto.getCost());

		for (Item itm : items) {

			if (itm.getItemId() == itemdto.getItemId()) {

				itm.setItemName(itemdto.getItemName());
				itm.setCost(itemdto.getCost());

				return itemRepo.save(itm);
			}

		}

		throw new ItemException("Item is not Present");

	}
	

	@Override
	public Item removeItem(Integer itemId, String key) throws ItemException, RestaurantException {

		CurrentUserSession curr = sessionrepo.findByUuid(key);

		if (curr == null)
			throw new RestaurantException("No restaurant Logged in with this key");

		if (curr.getRole().equalsIgnoreCase("customer"))
			throw new RestaurantException("You are not authorized");

		Restaurant restaurant = rr.findById(curr.getUserId()).orElseThrow(() -> new RestaurantException(""));

		List<Item> items = restaurant.getItemList();

		for (Item itm : items) {

			if (itm.getItemId() == itemId) {

				items.remove(itm);

				itemRepo.delete(itm);

				rr.save(restaurant);

				return itm;

			}

		}

		throw new ItemException("Item does not exist with this id");

	}

	@Override
	public List<ItemDTO> viewAllItems(String key) throws ItemException,RestaurantException {
	   
		CurrentUserSession curr = sessionrepo.findByUuid(key);

		if (curr == null)
			throw new RestaurantException("No restaurant Logged in with this key");

		if (curr.getRole().equalsIgnoreCase("customer"))
			throw new RestaurantException("You are not authorized");

		Restaurant restaurant = rr.findById(curr.getUserId()).orElseThrow(() -> new RestaurantException(""));
		
		
		List<Item> items = itemRepo.findAll();
		
		
		if(items.isEmpty()) throw new ItemException("No Items Added");
	    List<ItemDTO> itemsdto = new ArrayList<>();
	    
	    for(Item i : items ) {
	    	ItemDTO idto = new ItemDTO();
	    	idto.setCost(i.getCost());
	    	idto.setItemId(i.getItemId());
	    	idto.setItemName(i.getItemName());
	    	
	    	itemsdto.add(idto);
	    }
	    
	    
	    return itemsdto;
	}

}
