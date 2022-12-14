package com.masai.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalException {
	
	//Exception Handling For Validation
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetail> dataValid(MethodArgumentNotValidException me){
		
		MyErrorDetail detail = new MyErrorDetail();
		detail.setTimeStamp(LocalDateTime.now());
		detail.setMessage("Validation error");
		detail.setDetails(me.getBindingResult().getFieldError().getDefaultMessage());
		
		return new ResponseEntity<MyErrorDetail>(detail, HttpStatus.BAD_REQUEST);
		
	}
	
	//No handler found exception(any invalid URI)
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetail> notFoundHandler(NoHandlerFoundException se, WebRequest wr){
		
		MyErrorDetail detail = new MyErrorDetail();
		detail.setTimeStamp(LocalDateTime.now());
		detail.setMessage(se.getMessage());
		detail.setDetails(wr.getDescription(false));
		
		return new ResponseEntity<MyErrorDetail>(detail, HttpStatus.BAD_REQUEST);
	}
	
	//Exception Handling For Generic Exception
		@ExceptionHandler(Exception.class)
		public ResponseEntity<MyErrorDetail> studentEx(Exception se, WebRequest wr){
			
			MyErrorDetail detail = new MyErrorDetail();
			detail.setTimeStamp(LocalDateTime.now());
			detail.setMessage(se.getMessage());
			detail.setDetails(wr.getDescription(false));
			
			return new ResponseEntity<MyErrorDetail>(detail, HttpStatus.BAD_REQUEST);
		}
	
	//Exception Handling For Bill
	@ExceptionHandler(BillException.class)
	public ResponseEntity<MyErrorDetail> studentEx(BillException se, WebRequest wr){
		
		MyErrorDetail detail = new MyErrorDetail();
		detail.setTimeStamp(LocalDateTime.now());
		detail.setMessage(se.getMessage());
		detail.setDetails(wr.getDescription(false));
		
		return new ResponseEntity<MyErrorDetail>(detail, HttpStatus.BAD_REQUEST);
	}
	
	//Exception Handling For OrderDetails
	@ExceptionHandler(OrderDetailException.class)
	public ResponseEntity<MyErrorDetail> studentEx(OrderDetailException se, WebRequest wr){
		
		MyErrorDetail detail = new MyErrorDetail();
		detail.setTimeStamp(LocalDateTime.now());
		detail.setMessage(se.getMessage());
		detail.setDetails(wr.getDescription(false));
		
		return new ResponseEntity<MyErrorDetail>(detail, HttpStatus.BAD_REQUEST);
	}
	
	
	
	
}
