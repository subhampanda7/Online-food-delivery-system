package com.masai.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
	
private Integer itemId;
	
//	@NotBlank(message = "Item Name is Mandatory")
	private String itemName;
	
//	@NotNull
	private Double cost;
	
	@JsonIgnore
	private RestaurantDTO restDTO;
	

}
