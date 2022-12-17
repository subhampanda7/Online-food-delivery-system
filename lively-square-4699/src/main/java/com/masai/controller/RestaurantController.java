package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.dto.RestaurantDTO;
import com.masai.model.Restaurant;
import com.masai.service.ItemService;
import com.masai.service.RestaurantService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class RestaurantController {
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private ItemService itemService;
	
	
	
	
	@PostMapping("/restaurants")
	public ResponseEntity<Restaurant> addRestaurantHandler(@RequestBody RestaurantDTO resdto){
		
		Restaurant restaurant2 = restaurantService.addRestaurant(resdto);
		
		return new ResponseEntity<Restaurant>(restaurant2, HttpStatus.CREATED);
	}
	
	@PutMapping("/restaurants")
	public ResponseEntity<Restaurant> updateRestaurantHandler(@RequestBody RestaurantDTO resdto ,@RequestParam(required=false) String key) {
		
		Restaurant restaurant2 = restaurantService.updateRestaurant(resdto, key);
		
		return new ResponseEntity<Restaurant>(restaurant2, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/restaurants/{id}")
	public ResponseEntity<Restaurant> deleteRestaurantHandler(@PathVariable("id") Integer id, @RequestParam(required=false) String key){
		
		Restaurant restaurant2 = restaurantService.removeRestaurant(id, key);
		
		return new ResponseEntity<Restaurant>(restaurant2, HttpStatus.ACCEPTED);
		
	}
	
	
	@GetMapping("/restaurants/{id}")
	public ResponseEntity<RestaurantDTO>  viewRestaurantHandler(@PathVariable("id") Integer id){
		
		RestaurantDTO dto = restaurantService.viewRestaurant(id);
		
		return new ResponseEntity<RestaurantDTO>(dto, HttpStatus.OK);
		
	}
	
	

}
