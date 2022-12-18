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
import com.masai.repository.CustomerRepo;
import com.masai.repository.FoodCartDao;
import com.masai.repository.FoodCartItemRepo;
import com.masai.repository.ItemRepo;
import com.masai.repository.SessionRepo;


@Service
public class CartServiceImpl implements CartService {


	@Autowired
	private ItemRepo itemDao;
	
	@Autowired
	private FoodCartDao fcd;

	@Autowired
	private SessionRepo sessionrepo;
	
	@Autowired
	private CustomerRepo customerrepo;
	
	@Autowired
	private FoodCartItemRepo fcir;
	
	
	
	@Override
	public FoodCart addItemToCart(Integer itemId, String key) throws RestaurantException, ItemException {
		
		CurrentUserSession curr = sessionrepo.findByUuid(key);
		
		if(curr == null) throw new RestaurantException("No Customer Logged in with this key");
		
		if(curr.getRole().equalsIgnoreCase("restaurant")) throw new RestaurantException("You are not authorized");
		
		Customer customer = customerrepo.findById(curr.getUserId())
				.orElseThrow(() -> new RestaurantException(""));
		
		Item item = itemDao.findById(itemId)
			.orElseThrow(() ->new ItemException("Item id not valid"));
		
		FoodCart fc = customer.getFoodCart();
		
		List<FoodCartItems> fciList =fc.getItemList();
		
		
		FoodCartItems fci = fcir.sameItem(fc, item);

		
		if(fci == null) {
			
			fci = new FoodCartItems();
			
			fci.setFc(fc);
			fci.setItem(item);
			fci.setQuantity(1);	
			
			fciList.add(fci);
			
			fc.setItemList(fciList);
			
			fcd.save(fc);
			
		}else {
			Integer quan = fci.getQuantity();
			
			fci.setQuantity(quan + 1);
		}
		
		customerrepo.save(customer);
		
		return fc;
	
	}
	
	
	
	@Override
	public FoodCart removeItem(Integer itemId, String key) throws RestaurantException, ItemException, CartException{
	
		CurrentUserSession curr = sessionrepo.findByUuid(key);
		
		if(curr == null) throw new RestaurantException("No Customer Logged in with this key");
		
		if(curr.getRole().equalsIgnoreCase("restaurant")) throw new RestaurantException("You are not authorized");
		
		Customer customer = customerrepo.findById(curr.getUserId())
				.orElseThrow(() -> new RestaurantException("Enter valid key"));
		
		Item item = itemDao.findById(itemId)
				.orElseThrow(() -> new ItemException("There is no Item with this Id"));

		FoodCartItems fci = fcir.sameItem(customer.getFoodCart(), item);
		
		if(fci == null) {
			throw new CartException("This Item is added in your cart");
		}
		
		customer.getFoodCart().getItemList().remove(fci);
		
		fcir.delete(fci);

		return customer.getFoodCart();
		
	}
	
	

}
