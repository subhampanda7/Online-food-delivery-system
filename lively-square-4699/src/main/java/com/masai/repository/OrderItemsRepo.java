package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.OrderItems;

public interface OrderItemsRepo extends JpaRepository<OrderItems, Integer>{

}
