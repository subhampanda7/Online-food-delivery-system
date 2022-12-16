
package com.masai.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.masai.model.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer>{

//	@Query(value = "select * from customer c where c.customer_id=:customerId")
	public Customer findByCustomerId(Integer customerId);

	public List<Customer> findByEmail(String email);

	public Customer findByMobileNumber(String mobileNo);

}

