package com.masai.dto;

import java.util.ArrayList;
import java.util.List;

import com.masai.model.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

	private Integer customerId;
	private String name;
	private Integer age;
	private String gender;
	private String mobileNumber;
	private String email;

	private List<Address> addresses = new ArrayList<>();

}
