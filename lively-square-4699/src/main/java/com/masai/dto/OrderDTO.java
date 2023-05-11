package com.masai.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.masai.model.Address;
import com.masai.model.Bill;
import com.masai.model.OrderItems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
	
	private Integer orderId;
	private LocalDateTime orderDate;
	private Boolean orderStatus;
	
	private Address orderAddress;
	
	private RestSimpleDTO restaurant;
	
	private List<OrderItems> itemList = new ArrayList<>();
	
	private  Bill bill;

}
