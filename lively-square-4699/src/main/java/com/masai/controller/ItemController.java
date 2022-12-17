package com.masai.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.dto.ItemDTO;
import com.masai.exceptions.ItemException;
import com.masai.exceptions.RestaurantException;
import com.masai.model.Item;
import com.masai.model.Restaurant;
import com.masai.service.CustomerService;
import com.masai.service.ItemService;
import com.masai.service.RestaurantService;

@RestController
public class ItemController {
	
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ItemService itemService;
	
	
	//Verified fully - restaurant key
	@PostMapping("/items/add")
	public ResponseEntity<Item> addItemHandller(@RequestBody ItemDTO item, @RequestParam String key) throws ItemException, RestaurantException{
	
       	Item newItem = itemService.addItem(item, key);
		
		return new ResponseEntity<Item>(newItem, HttpStatus.CREATED);
		
	}
	
	//Verified fully - restaurant key
	@PutMapping("/items/update")
	public ResponseEntity<Item> updateItemHandller(@Valid @RequestBody ItemDTO item, @RequestParam String key) throws ItemException, RestaurantException{
			
		Item updatedItem = itemService.updateItem(item, key);
		
			
		return new ResponseEntity<Item>(updatedItem, HttpStatus.OK);
			
	}
	
	//Verified fully - item id
	@GetMapping("/search/items")
	public ResponseEntity<ItemDTO> viewItemHandller(@RequestParam Integer itemKey) throws ItemException {

		ItemDTO viewItem = itemService.viewItem(itemKey);

		return new ResponseEntity<ItemDTO>(viewItem, HttpStatus.OK);
	}
	
	//Verified fully - restaurant key
	@DeleteMapping("/items/delete")
	public ResponseEntity<Item> removeItemHandller(@RequestParam Integer itemId, @RequestParam String key) throws ItemException, RestaurantException{
			
		Item deletedItem = itemService.removeItem(itemId, key);
			
		return new ResponseEntity<Item>(deletedItem, HttpStatus.OK);
	}
	
	//Verified fully - restaurant key
	@GetMapping("/items/all")
	public ResponseEntity<List<ItemDTO>> viewAllItems(String key) throws ItemException,RestaurantException{
		
		return new ResponseEntity<>(itemService.viewAllItems(key),HttpStatus.ACCEPTED);
		
	}
	
	//Verified fully - restaurant id
	@GetMapping("/search/items/restaurant")
	public ResponseEntity<List<ItemDTO>> viewAllItemByRestaurantHandler(@RequestParam Integer restId)
			throws ItemException, RestaurantException {

		return new ResponseEntity<List<ItemDTO>>(itemService.viewAllItemsByRestaurant(restId), HttpStatus.OK);
	}
	
	

}
