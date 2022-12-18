
package com.masai.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	
//	@NotNull(message="First name could not be empty")
	private String firstName;
	
	private String lastName;
	
//	@Min(value=18, message="Age should be above 18")
	private Integer age;
	
	private String gender;
	
//	@Size(min=10, max=13)
	private String mobileNumber;
	
//	@Email
	private String email;
	
	private String password;
	
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "addressId")
	private Address address;
	
	@OneToOne(cascade = CascadeType.ALL)
	private FoodCart foodCart;
	
}
