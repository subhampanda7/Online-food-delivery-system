package com.masai.dto;

import com.masai.model.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {
	
	private Integer restaurantId;
	private String restaurantName;
	private String managerName;
	private String contactNumber;
	private String password;

	private Address address;

	
}
