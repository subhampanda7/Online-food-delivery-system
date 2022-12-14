package com.masai.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer billId;
	private LocalDateTime billdate;
	private Integer totalItem;
	private Double totalCost;
	 
	@OneToOne
	@JoinColumn(name = "orderId")
	private OrderDetails orderDetails;
}
