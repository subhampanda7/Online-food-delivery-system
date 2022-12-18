package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.CartException;
import com.masai.exceptions.ItemException;
import com.masai.exceptions.RestaurantException;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.FoodCart;
import com.masai.model.FoodCartItems;
import com.masai.model.Item;
import com.masai.repository.CustomerDao;
import com.masai.repository.FoodCartDao;
import com.masai.repository.FoodCartItemsDao;
import com.masai.repository.ItemDao;
import com.masai.repository.SessionDao;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private FoodCartDao foodCartDao;
	
	@Autowired
	private FoodCartItemsDao foodCartItemsDao;
	
	@Autowired
	private SessionDao sessionDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private ItemDao itemDao;

	
	//Verified fully
	@Override
	public FoodCart addItemToCart(Integer itemId, String key) throws RestaurantException, ItemException {
		
		CurrentUserSession curr = sessionDao.findByUuid(key);
		
		if(curr == null) throw new RestaurantException("No Customer Logged in with this key..");
		
		if(curr.getRole().equalsIgnoreCase("restaurant")) throw new RestaurantException("You are not authorized");
		
		Customer customer = customerDao.findById(curr.getUserId())
				.orElseThrow(() -> new RestaurantException(""));
		
		Item item = itemDao.findById(itemId)
			.orElseThrow(() ->new ItemException("Item id not valid!!!"));
		
		FoodCart fc = customer.getFoodCart();
		
		System.out.println("hi");
		
		List<FoodCartItems> fciList =fc.getItemList();
		
		
		FoodCartItems cartItems = foodCartItemsDao.sameItem(fc, item);

		
		if(cartItems == null) {
			
			cartItems = new FoodCartItems();
			
			cartItems.setFc(fc);
			cartItems.setItem(item);
			cartItems.setQuantity(1);	
			
			fciList.add(cartItems);
			
			fc.setItemList(fciList);
			
			foodCartDao.save(fc);
			
		}else {
			Integer quan = cartItems.getQuantity();
			
			cartItems.setQuantity(quan + 1);
		}
		
		customerDao.save(customer);
		
		return fc;
	
	}
	
	//Verified fully
	@Override
	public FoodCart increaseQuantity(Integer itemId, Integer quantity, String key) throws RestaurantException, ItemException, CartException {
		
		CurrentUserSession curr = sessionDao.findByUuid(key);
		
		if(curr == null) throw new RestaurantException("No Customer Logged in with this key");
		
		if(curr.getRole().equalsIgnoreCase("restaurant")) throw new RestaurantException("You are not authorized");
		
		Customer customer = customerDao.findById(curr.getUserId())
				.orElseThrow(() -> new RestaurantException(""));
		
		Item item = itemDao.findById(itemId)
				.orElseThrow(() -> new ItemException("There is no Item with this Id.."));

		FoodCartItems cartItems = foodCartItemsDao.sameItem(customer.getFoodCart(), item);
		
		if(cartItems == null) {
			throw new CartException("First Add the item in your cart");
		}
		
		Integer quan = cartItems.getQuantity();
		
		cartItems.setQuantity(quan + quantity);
		
		customerDao.save(customer);

		return customer.getFoodCart();
	}
	
	//Verified fully
	@Override
	public FoodCart reduceQuantity(Integer itemId, Integer quantity, String key) throws RestaurantException, ItemException, CartException {
		
		CurrentUserSession curr = sessionDao.findByUuid(key);
		
		if(curr == null) throw new RestaurantException("No Customer Logged in with this key");
		
		if(curr.getRole().equalsIgnoreCase("restaurant")) throw new RestaurantException("You are not authorized");
		
		Customer customer = customerDao.findById(curr.getUserId())
				.orElseThrow(() -> new RestaurantException(""));
		
		Item item = itemDao.findById(itemId)
				.orElseThrow(() -> new ItemException("There is no Item with this Id"));

		FoodCartItems cartItems = foodCartItemsDao.sameItem(customer.getFoodCart(), item);
		
		if(cartItems == null) {
			throw new CartException("First Add the item in your cart");
		}
		
		Integer quan = cartItems.getQuantity();
		
		cartItems.setQuantity(quan - quantity);
		
		customerDao.save(customer);

		return customer.getFoodCart();
	}
	
	//Verified fully
	@Override
	public FoodCart removeItem(Integer itemId, String key) throws RestaurantException, ItemException, CartException {
		
		CurrentUserSession curr = sessionDao.findByUuid(key);
		
		if(curr == null) throw new RestaurantException("No Customer Logged in with this key");
		
		if(curr.getRole().equalsIgnoreCase("restaurant")) throw new RestaurantException("You are not authorized");
		
		Customer customer = customerDao.findById(curr.getUserId())
				.orElseThrow(() -> new RestaurantException(""));
		
		Item item = itemDao.findById(itemId)
				.orElseThrow(() -> new ItemException("There is no Item with this Id"));

		FoodCartItems cartItems = foodCartItemsDao.sameItem(customer.getFoodCart(), item);
		
		if(cartItems == null) {
			throw new CartException("This Item is added in your cart");
		}
		
		customer.getFoodCart().getItemList().remove(cartItems);
		
		foodCartItemsDao.delete(cartItems);

		return customer.getFoodCart();
	}
	
	//Verified fully
	@Override
	public String clearCart(String key) throws RestaurantException, CartException {
		
		CurrentUserSession curr = sessionDao.findByUuid(key);
		
		if(curr == null) throw new RestaurantException("No customer logged in with this key");
		
		if(curr.getRole().equalsIgnoreCase("restaurant")) throw new RestaurantException("You are not authorized");
		
		Customer customer = customerDao.findById(curr.getUserId())
				.orElseThrow(() -> new RestaurantException(""));

		List<FoodCartItems> fciList = foodCartItemsDao.findByFc(customer.getFoodCart());
		
		if(fciList.size() == 0) {
			throw new CartException("Your cart is already empty");
		}
		
		customer.getFoodCart().getItemList().clear();
		
		System.out.println(customer.getFoodCart().getItemList());
		
		for(FoodCartItems fcitCartItems: fciList) {
			foodCartItemsDao.delete(fcitCartItems);
		}
		
		return "Cart Successfully cleared";
		
	}

	
	
	
	

}
