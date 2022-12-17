package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.FoodCart;
import com.masai.model.Item;
import com.masai.service.CategoryServiceImpl;
import com.masai.service.FoodCartServiceImpl;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController("/foodcart")
public class FoodCartController {
	
//	@Autowired
//	private FoodCartServiceImpl FCSI;
//	
//	@Autowired
//	private CategoryServiceImpl categoryService;
//	
//	@PostMapping("/additemtocart")
//	public ResponseEntity<FoodCart> addItemToCartHandler(@RequestBody FoodCart cart,@RequestBody Item item) {
//		
//		FoodCart fc = FCSI.addItemToCart(cart, item);
//		
//		return new ResponseEntity<FoodCart>(fc,HttpStatus.ACCEPTED);
//		
//	}
//	
//	@PostMapping("/increasequantity")
//	public ResponseEntity<FoodCart> increaseQuantityHandler(@RequestBody FoodCart foodcart,@RequestBody Item item,@PathVariable ("quantity") Integer quan){
//		
//		FoodCart fc = FCSI.increaseQuantity(foodcart, item, quan);
//		
//		return new ResponseEntity<FoodCart>(fc,HttpStatus.OK);
//		
//	}
//	
//	@PostMapping("/decreasequantity")
//	public ResponseEntity<FoodCart> reduceQuantityHandler(@RequestBody FoodCart foodcart,@RequestBody Item item,@PathVariable ("quantity") Integer quan){
//		
//		FoodCart fc = FCSI.reduceQuantity(foodcart, item, quan);
//		
//		return new ResponseEntity<FoodCart>(fc,HttpStatus.OK);
//		
//	}
//	
//	
//	@DeleteMapping("/remove")
//	public ResponseEntity<FoodCart> removeItemHandler(@RequestBody FoodCart foodcart,@RequestBody Item item){
//		
//		FoodCart fc = FCSI.removeItem(foodcart, item);
//		
//		return new ResponseEntity<FoodCart>(fc,HttpStatus.OK);
//	}
//	
//	@DeleteMapping("/clear")
//	public ResponseEntity<FoodCart> clearCartHandler(@RequestBody FoodCart foodcart){
//		
//		FoodCart fc = FCSI.clearCart(foodcart);
//		
//		return new ResponseEntity<FoodCart>(fc,HttpStatus.OK);
//	}
//	
//	
//	
//	
	
	
	

}
