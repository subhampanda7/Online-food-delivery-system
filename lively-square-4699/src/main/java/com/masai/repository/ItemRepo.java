package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.masai.model.Category;
import com.masai.model.Item;
import com.masai.model.Restaurant;

public interface ItemRepo extends JpaRepository<Item, Integer> {

	@Query("select i.restaurant from Item i where i.itemName like %?1%")
	public List<Restaurant> searchByItemNameContaining(String itemName);

	public List<Item> findByItemNameContaining(String itemName);

	public List<Item> findByItemName(String itemName);

	public List<Item> findByCategory(Category category);

}
