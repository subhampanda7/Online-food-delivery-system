package com.masai.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustAddDto {
	
	
	private Integer customerId;
	
	@Size(min = 3, message = "Customer name is mandatory")
	private String fullName;
	
	@Min(value = 13, message = "Customer age min should be 13")
	private Integer age;
	
	private String gender;
	
	@NotBlank
	@Size(min = 10, max = 13, message = "mobile must be between 10 to 13 digit")
	private String mobileNumber;
	
	@NotBlank
	@Email(message = "Give valid Email Id")
	private String email;
	
	@NotBlank
	@Size(min = 4, message = "Password Size must be greater than 4")
	private String password;
	
	
}
