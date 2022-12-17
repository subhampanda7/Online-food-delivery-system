package com.masai.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.model.FoodCart;
import com.masai.model.Item;
import com.masai.repository.FoodCartDao;


@Service
public class FoodCartServiceImpl implements FoodCartService{
	
	@Autowired
	private FoodCartDao foodCartDao;
	
	

//	@Override
//	public FoodCart addItemToCart(FoodCart foodCart,Item item) {
//		// TODO Auto-generated method stub
//		List<Item>  items = foodCart.getItems();
//		
//		items.add(item);
//		
//         foodCart.setItems(items);
//         
//         foodCartDao.save(foodCart);
//         
//         return foodCart;
//         
//	}

//	@Override
//	public FoodCart increaseQuantity(FoodCart cart, Item item, Integer quantity) /*throw ItemException*/ {
//		// TODO Auto-generated method stub
//		
//		List<Item> items = cart.getItems();
//		
//		boolean flag = true;
//		
//		for( Item i : items) {
//			if(i.getItemId()==item.getItemId()) {
//				i.setQuantity(i.getQuantity()+quantity);
//				flag = false;
//			}
//		}
////		
////		if(flag == true) {
////			throw new ItemException("item Not found....");
////		}
//		
//		foodCartDao.save(cart);
//		
//		return cart;
//	}

//	@Override
//	public FoodCart reduceQuantity(FoodCart cart, Item item, Integer quantiaty) /*throw ItemException*/{
//		// TODO Auto-generated method stub
//		
//		
//		List<Item> items = cart.getItems();
//		
//		boolean flag = true;
//		
//		for( Item i : items) {
//			if(i.getItemId()==item.getItemId()) {
//				
////				if(i.getQuantity()<quantity) {
////					throw new ItemException("Item is not suffcient.....");
////				}
//				
//				
//				i.setQuantity(i.getQuantity()+ quantiaty);
//				flag = false;
//			}
//		}
		
//		if(flag == true) {
//			throw new ItemException("item Not found....");
//		}
		
//		foodCartDao.save(cart);
//		
//		return cart;
//		
//		
//	}

//	@Override
//	public FoodCart removeItem(FoodCart cart, Item item) /*throw ItemException*/{
//		// TODO Auto-generated method stub
//		
//		
//		List<Item> items = cart.getItems();
//		
//		boolean flag = false;
//		
//		for( Item i : items) {
//			if(i.getItemId()==item.getItemId()) {
//				flag=items.remove(item);
//				
//			}
//		}
		
//		if(flag == false) {
//			throw new ItemException("item Not found....");
//		}
		
//		foodCartDao.save(cart);
//		
//		return cart;
//		
//	}
//
//	@Override
//	public FoodCart clearCart(FoodCart cart) {
//		// TODO Auto-generated method stub
//		
//		cart.getItems().clear();
//		
//		return foodCartDao.save(cart);
//		
//		
//	}

}
