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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.dto.ItemDTO;
import com.masai.dto.RestaurantAddDTO;
import com.masai.dto.RestaurantDTO;
import com.masai.exceptions.ItemException;
import com.masai.exceptions.RestaurantException;
import com.masai.model.Item;
import com.masai.model.Restaurant;
import com.masai.service.CustomerService;
import com.masai.service.ItemService;
import com.masai.service.RestaurantService;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
	
	@Autowired
	private RestaurantService rs;
	
	@Autowired
	private CustomerService cs;
	
	@Autowired
	private ItemService its;
	
	
	//Restaurant
	@PostMapping("/create")
	public ResponseEntity<Restaurant> addRestaurant(@Valid @RequestBody RestaurantAddDTO resDto) throws RestaurantException{
		
		Restaurant rest = rs.addRestaurant(resDto);
		
		return new ResponseEntity<Restaurant>(rest, HttpStatus.CREATED);
		
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<Restaurant> updateRestaurant(@Valid @RequestBody RestaurantAddDTO res, @RequestParam String key) throws RestaurantException{
		Restaurant rest =rs.updateRestaurant(res, key);
		
		return new ResponseEntity<Restaurant>(rest,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/view/all")
	public ResponseEntity<RestaurantDTO> viewRestaurant(Integer resId) throws RestaurantException{
		
		RestaurantDTO dto = rs.viewRestaurant(resId);
		
		return new ResponseEntity<RestaurantDTO>(dto, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/delete")
	public ResponseEntity<Restaurant> removeRestaurant(@RequestParam Integer resId, @RequestParam String key) throws RestaurantException{
		 Restaurant rest = rs.removeRestaurant(resId, key);
		
		return new ResponseEntity<Restaurant>(rest,HttpStatus.OK);
	}
	
	
	
	
	//Item
	@PostMapping("/item/add")
	public ResponseEntity<Item> addItemHandller(@Valid @RequestBody ItemDTO item, @RequestParam String key) throws ItemException, RestaurantException{
	
       	Item itm = its.addItem(item, key);
		
		return new ResponseEntity<Item>(itm, HttpStatus.OK);
		
	}
		
		
	@PutMapping("/item/update")
	public ResponseEntity<Item> updateItemHandller(@Valid @RequestBody ItemDTO item, @RequestParam String key) throws ItemException, RestaurantException{
			
		Item it = its.updateItem(item, key);
		
			
		return new ResponseEntity<Item>(it, HttpStatus.OK);
			
	}
	
	@DeleteMapping("/item/delete")
	public ResponseEntity<Item> removeItemHandller(@RequestParam Integer itemId, @RequestParam String key) throws ItemException, RestaurantException{
			
		Item it = its.removeItem(itemId, key);
			
		return new ResponseEntity<Item>(it, HttpStatus.OK);
	}
	
	
	@GetMapping("/item/all")
	public ResponseEntity<List<ItemDTO>> viewAllItems(String key) throws ItemException,RestaurantException{
		
		return new ResponseEntity<>(its.viewAllItems(key),HttpStatus.ACCEPTED);
		
	}
}
