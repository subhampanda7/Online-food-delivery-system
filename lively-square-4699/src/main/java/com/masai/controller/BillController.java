package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Bill;
import com.masai.service.BillService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class BillController {
	
	@Autowired
	private BillService billService;
	
	//Add bill
	@PostMapping("/bills")
	public ResponseEntity<Bill> addBillHandler(@RequestBody Bill bill){
		
		Bill bill2 = billService.addBill(bill);
		
		return new ResponseEntity<Bill>(bill2, HttpStatus.ACCEPTED);
		
	}
	
	//Update bill
	@PutMapping("/bills/{id}")
	public ResponseEntity<Bill> updateBillHandler(Bill bill){
		
		Bill bill2 = billService.updateBill(bill);
		
		return new ResponseEntity<Bill>(bill2, HttpStatus.ACCEPTED);
	}
	
	//Delete bill
	@DeleteMapping("/bills/{id}")
	public ResponseEntity<Bill> removeBillHandler(@PathVariable("id") Integer id){
		
		Bill bill = billService.removeBill(id);
		
		return new ResponseEntity<Bill>(bill, HttpStatus.OK);
		
	}
	
	//Bill total cost
	@GetMapping("/bills/cost/{id}")
	public ResponseEntity<Double> totalCost(@PathVariable("id") Integer id){
		
		Double total = billService.totalCost(id);
		
		return new ResponseEntity<Double>(total, HttpStatus.OK);
	}
	
	//View all bills
	@GetMapping("/bills")
	public ResponseEntity<List<Bill>> viewAllBillHandler(){
		
		List<Bill> bills = billService.viewAllBills();
		
		return new ResponseEntity<List<Bill>>(bills, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	

}
