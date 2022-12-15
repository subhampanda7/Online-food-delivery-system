package com.masai.service;

import java.util.List;

import com.masai.exceptions.BillException;
import com.masai.model.Bill;

public interface BillService {
	
	
	public Bill addBill(Bill bill) throws BillException;
	
	public Bill updateBill(Bill bill) throws BillException;
	
	public Bill removeBill(Integer id) throws BillException;
	
	public List<Bill> viewBillByCustomerId(Integer customerID) throws BillException;
	
	public Double totalCost(Integer id) throws BillException;
	
	public List<Bill> viewAllBills() throws BillException;
	
	 
	
}
