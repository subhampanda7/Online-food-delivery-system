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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
@EqualsAndHashCode
public class Restaurant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer restaurantId;
	private String restaurantName;
	private String managerName;
	private String contactNumber;
	private String email;
	private String password;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant")
	@JsonIgnore
	private List<Item> itemList = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant")
	@JsonIgnore
	private List<OrderDetails> orderLists = new ArrayList<>();
	
	@ManyToMany(targetEntity = Customer.class, cascade = CascadeType.ALL) 
	@JsonIgnore
	private Set<Customer> customers = new HashSet<>();

	@OneToMany(targetEntity = Category.class, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Category> categories = new HashSet<>();
	
}
