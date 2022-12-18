package com.masai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestSimpleDTO {
	
	private Integer restaurantId;
	private String restaurantName;
	private String contactNumber;
	
}
