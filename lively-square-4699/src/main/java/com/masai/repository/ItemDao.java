package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Category;
import com.masai.model.Item;

public interface ItemDao extends JpaRepository<Item, Integer> {

    public List<Item> findByCategory(Category cat);

}