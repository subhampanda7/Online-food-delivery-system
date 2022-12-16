package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.CategoryException;
import com.masai.model.Category;
import com.masai.service.CategoryServiceImpl;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController("/category")
public class CategoryController {
	
	@Autowired
	private CategoryServiceImpl categoryService;
	
	@PostMapping("/addcategory")
	public ResponseEntity<Category> addCategoryHandler(@RequestBody Category cat) throws CategoryException{
		
		Category categ = categoryService.addCategory(cat);
		
		return new ResponseEntity<Category>(categ, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Category> updateCategoryHandler(@RequestBody Category cate) throws CategoryException {
		
		Category categ = categoryService.updateCategory(cate);
		
		return new ResponseEntity<Category>(categ,HttpStatus.ACCEPTED);
		
	}
	
	
	@DeleteMapping("/delete")
	public ResponseEntity<Category> removeCategoryHandler(@RequestBody Category category) throws CategoryException{
		
		Category cat = categoryService.removeCategory(category);
		
		return new ResponseEntity<Category>(cat,HttpStatus.OK);
		
	}
	
	@GetMapping("/get")
	public ResponseEntity<Category> viewCategoryHandler(@RequestBody Category cate) throws CategoryException{
		
		Category category = categoryService.viewCategory(cate);
		
		return new ResponseEntity<Category>(category,HttpStatus.OK);
		
	}
	
	public ResponseEntity<List<Category>> viewAllCategoryHandler() throws CategoryException{
		
		List<Category> lists = categoryService.viewAllCategory();
		
		return new ResponseEntity<List<Category>>(lists,HttpStatus.OK);
	}
	
	
	
	
	
	

}
