package com.masai.model;

import java.util.List;

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
public class FoodCart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cartId;

	@OneToOne
	@JsonIgnore
	private Customer customer;

	@OneToMany(cascade = CascadeType.ALL)
	private List<FoodCartItems> itemList;

	@Override
	public String toString() {
		return "FoodCart [cartId=" + cartId + ", itemList=" + itemList + "]";
	}

}
