package com.masai.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
	
	
	private Integer itemId;
	
	@NotBlank(message = "Item name is mandatory")
	private String itemName;
	
	@NotNull
	private Double cost;
	
	@JsonIgnore
	private RestaurantDTO restDTO;
	
}
