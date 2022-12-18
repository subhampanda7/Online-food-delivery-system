package com.masai.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	private String fullName;
	private Integer age;
	private String gender;
	private String mobileNumber;
	private String email;
	
	@JsonIgnore
	private String password;
	
	@OneToMany(targetEntity = Address.class, cascade = CascadeType.ALL)
	private Set<Address> addresses = new HashSet<>();
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	private List<OrderDetails> orders = new ArrayList<>();
	
	
	@OneToOne(cascade = CascadeType.ALL)
	private FoodCart foodCart;


	public Customer(Integer customerId, String fullName, Integer age, String gender, String mobileNumber, String email,
			String password, FoodCart foodCart) {
		super();
		this.customerId = customerId;
		this.fullName = fullName;
		this.age = age;
		this.gender = gender;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.password = password;
		this.foodCart = foodCart;
	}


	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", fullName=" + fullName + ", age=" + age + ", gender=" + gender
				+ ", mobileNumber=" + mobileNumber + ", email=" + email + ", password=" + password + ", addresses="
				+ addresses + ", foodCart=" + foodCart + "]";
	}
	
	
	
}
