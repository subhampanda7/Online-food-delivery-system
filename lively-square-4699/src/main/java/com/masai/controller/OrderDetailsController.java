package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.OrderDetails;
import com.masai.service.OrderDetailsService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class OrderDetailsController {
	
	@Autowired
	private OrderDetailsService detailsService;
	
	@PostMapping("/orders")
	public ResponseEntity<OrderDetails> addOrderHandler(@RequestBody OrderDetails orderDetails){
		
		OrderDetails details = detailsService.addOrder(orderDetails);
		
		return new ResponseEntity<OrderDetails>(details, HttpStatus.CREATED);
		
	}
	
	//Update order
	@PutMapping("/orders")
	public ResponseEntity<OrderDetails> updateOrderHandler(@RequestBody OrderDetails details){
		
		OrderDetails details2 = detailsService.updateOrder(details);
		
		return new ResponseEntity<OrderDetails>(details2, HttpStatus.OK);
		
	}
	
	//Remove Order
	@DeleteMapping("/orders/{id}")
	public ResponseEntity<OrderDetails> removeOrderHandler(@PathVariable("id") Integer id){
		
		OrderDetails details2 = detailsService.removeOrder(id);
		
		return new ResponseEntity<OrderDetails>(details2, HttpStatus.ACCEPTED);
		
	}
	
	//View Order
	@GetMapping("/orders/{id}")
	public ResponseEntity<OrderDetails> viewOrderHandler(@PathVariable("id") Integer id){
		
		OrderDetails details = detailsService.viewOrder(id);
		
		return new ResponseEntity<OrderDetails>(details, HttpStatus.OK);
	}
	
	
	
	

}
