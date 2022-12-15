package com.masai.model;

import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Restaurant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer restaurantId;
	private String restaurantName;
	private String managerName;
	private String contactNumber;


	@ManyToMany
	private List<Item> items;
	
	@OneToOne
	@JoinColumn(name = "addressId")
	private Address address;



}
