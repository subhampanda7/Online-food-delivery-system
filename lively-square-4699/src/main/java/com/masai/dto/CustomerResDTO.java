package com.masai.dto;

import java.util.List;

import com.masai.model.OrderDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerResDTO {
	
	private String fullName;
	private Integer age;
	private String gender;

	public CustomerResDTO(String fullName, Integer age, String gender) {
		super();
		this.fullName = fullName;
		this.age = age;
		this.gender = gender;
	}
	

	private List<OrderDetails> orders;

}
