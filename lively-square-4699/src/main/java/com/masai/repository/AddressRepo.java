package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.masai.model.Address;

public interface AddressRepo extends JpaRepository<Address, Integer>{
	
	public List<Address> findByCity(String city);
	
	@Query("Select a from Address a where a.street=?1 and a.pincode=?2 and a.building=?3")
	public Address findByStreetPincodeBuilding(String stree, String pincode, String building);
	
}
