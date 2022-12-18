package com.masai.dto;

import java.util.ArrayList;
import java.util.List;

import com.masai.model.Address;
import com.masai.model.Item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RestaurantDTO {
	
	private Integer restaurantId;
	private String restaurantName;
	private String contactNumber;
	private Address address;
	
	private List<Item> itemList = new ArrayList<>();
	
}
