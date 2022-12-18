package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.CartException;
import com.masai.exceptions.ItemException;
import com.masai.exceptions.RestaurantException;
import com.masai.model.FoodCart;
import com.masai.service.CartService;

@RestController
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@PutMapping("/cart/add") 
	public ResponseEntity<FoodCart> addItemToCartHandler(@RequestParam Integer itemId, @RequestParam String key) throws RestaurantException, ItemException {

		FoodCart fc = cartService.addItemToCart(itemId, key);

		return new ResponseEntity<FoodCart>(fc, HttpStatus.ACCEPTED);

	}
	
	
	@PutMapping("/cart/increase") 
	public ResponseEntity<FoodCart> increaseQuantityHandler(@RequestParam Integer itemId,
			@RequestParam Integer Quantity, @RequestParam String key)
			throws RestaurantException, ItemException, CartException {

		FoodCart fc = cartService.increaseQuantity(itemId, Quantity, key);

		return new ResponseEntity<FoodCart>(fc, HttpStatus.OK);
	}

	@PutMapping("/cart/reduce") 
	public ResponseEntity<FoodCart> reduceQuantityHandler(@RequestParam Integer itemId, @RequestParam Integer Quantity,
			@RequestParam String key) throws RestaurantException, ItemException, CartException {

		FoodCart fc = cartService.reduceQuantity(itemId, Quantity, key);

		return new ResponseEntity<FoodCart>(fc, HttpStatus.OK);
	}

	@DeleteMapping("/cart/remove") 
	public ResponseEntity<FoodCart> removeHandler(@RequestParam Integer itemId, @RequestParam String key)
			throws RestaurantException, ItemException, CartException {

		FoodCart fc = cartService.removeItem(itemId, key);

		return new ResponseEntity<FoodCart>(fc, HttpStatus.OK);
	}

	@DeleteMapping("/cart/clear") 
	public ResponseEntity<String> clearcartHandler(@RequestParam String key)
			throws RestaurantException, CartException {

		String fc = cartService.clearCart(key);

		return new ResponseEntity<String>(fc, HttpStatus.OK);
	}

}
