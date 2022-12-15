package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.BillException;
import com.masai.model.Bill;
import com.masai.repository.BillDao;

@Service
public class BillServiceImpl implements BillService{
	
	@Autowired
	private BillDao billDao;

	@Override
	public Bill addBill(Bill bill) throws BillException {
		
		if(bill != null) {
			return billDao.save(bill);
		} else {
			throw new BillException("Enter correct details");
		}
		
		
		
	}

	@Override
	public Bill updateBill(Bill bill) throws BillException {
		
		
		Optional<Bill> opt = billDao.findById(bill.getBillId());
		
		if(opt.isPresent()) {
			Bill bill2 = billDao.save(bill);
			return bill2;
		} else {
			throw new BillException("Invalid bill details");
		}
		
	}

	@Override
	public Bill removeBill(Integer id) throws BillException {
		
		Optional<Bill> opt = billDao.findById(id);
		
		if(opt.isPresent()) {
			
			Bill bill2 = opt.get();
			billDao.delete(bill2);
			
			return bill2;
			
		} else {
			throw new BillException("Bill not exist with id : "+id);
		}
		
	}

	@Override
	public List<Bill> viewBillByCustomerId(Integer customerID) throws BillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double totalCost(Integer id) throws BillException {
		
		Optional<Bill> opt = billDao.findById(id);
		
		if(opt.isPresent()) {
			Bill bill2 = opt.get();
			
			return bill2.getTotalCost();
		} else {
			throw new BillException("Bill not exist with id : "+id);
		}
		
	}

	@Override
	public List<Bill> viewAllBills() throws BillException {
		
		List<Bill> bills = billDao.findAll();
		
		if(bills.isEmpty()) {
			throw new BillException("No bills data exist in database");
		} else {
			return bills;
		}
			
		
	}

}
