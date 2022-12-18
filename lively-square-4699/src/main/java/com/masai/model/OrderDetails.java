package com.masai.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class OrderDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
	private LocalDateTime orderDate;
	private Boolean orderStatus;
	
	@OneToOne
	@JsonIgnore
	private Address orderAddress;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Customer customer;
	
	@ManyToOne
	@JsonIgnore
	private Restaurant restaurant;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "od")
	@JsonIgnore
	private List<OrderItems> itemList = new ArrayList<>();
	
	@OneToOne
	@JsonIgnore
	private  Bill bill;

	
}
