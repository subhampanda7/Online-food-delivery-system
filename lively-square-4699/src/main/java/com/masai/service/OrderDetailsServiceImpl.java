package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.OrderDetailException;
import com.masai.model.Customer;
import com.masai.model.OrderDetails;
import com.masai.model.Restaurant;
import com.masai.repository.OrderDetailsDao;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService{
	
	@Autowired
	private OrderDetailsDao detailsDao;

	@Override
	public OrderDetails addOrder(OrderDetails orderDetails) throws OrderDetailException {
		
		return detailsDao.save(orderDetails);
		
	}

	@Override
	public OrderDetails updateOrder(OrderDetails orderDetails) throws OrderDetailException {
		
		Optional<OrderDetails> opt = detailsDao.findById(orderDetails.getOrderId());
		
		if(opt.isPresent()) {
			
			OrderDetails details = detailsDao.save(orderDetails);
			
			return details;
			
		} else {
			throw new OrderDetailException("Order not exist with id "+orderDetails.getOrderId());
		}
		
	}

	@Override
	public OrderDetails removeOrder(Integer id) throws OrderDetailException {
		

		Optional<OrderDetails> opt = detailsDao.findById(id);
		
		if(opt.isPresent()) {
			
			OrderDetails details = opt.get();
			
			detailsDao.delete(details);
			
			return details;
		} else {
			throw new OrderDetailException("Order not exist with id "+id);
		}
		
		
	}

	@Override
	public OrderDetails viewOrder(Integer id) throws OrderDetailException {
		
		return detailsDao.findById(id).orElseThrow(()-> new OrderDetailException("Order details not exist with id "+id));
		
		
	}

	@Override
	public List<OrderDetails> viewAllOrders(Restaurant restaurant) throws OrderDetailException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDetails> viewAllOrders(Customer customer) throws OrderDetailException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
