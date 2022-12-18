package com.masai.dto;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.masai.model.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantAddDTO {
	
	private Integer restaurantId;
	
	@NotBlank(message = "Restaurant name is mandatory")
	private String restaurantName;
	
	private String managerName;
	
	@NotBlank
	@Size(min = 10, max = 13, message = "Mobile must be between 10 to 13 digit")
	private String contactNumber;
	
	@NotBlank
	@Email(message = "Give proper Email Id")
	private String email;
	
	@NotBlank
	@Size(min = 4, message = "Password Size must be greater than 4")
	private String password;
	
	@Valid
	private Address address;
	
}
