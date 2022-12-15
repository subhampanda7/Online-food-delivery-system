package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.OrderDetails;

@Repository
public interface OrderDetailsDao extends JpaRepository<OrderDetails, Integer>{

}
