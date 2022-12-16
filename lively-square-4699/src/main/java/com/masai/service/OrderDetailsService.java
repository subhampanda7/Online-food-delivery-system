package com.masai.service;

import java.util.List;

import com.masai.exceptions.OrderDetailException;
import com.masai.model.Customer;
import com.masai.model.OrderDetails;
import com.masai.model.Restaurant;

public interface OrderDetailsService {
	
	public OrderDetails addOrder(OrderDetails orderDetails) throws OrderDetailException;
	
	public OrderDetails updateOrder(OrderDetails orderDetails) throws OrderDetailException;
	
	public OrderDetails removeOrder(Integer id) throws OrderDetailException;
	
	public OrderDetails viewOrder(Integer id) throws OrderDetailException;
	
	public List<OrderDetails> viewAllOrders(Restaurant restaurant) throws OrderDetailException;
	
	public List<OrderDetails> viewAllOrders(Customer customer) throws OrderDetailException;
	
	

}
