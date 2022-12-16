package com.masai.service;

import java.util.List;

import com.masai.exceptions.CategoryException;
import com.masai.model.Category;


public interface CategoryService {
	
	public Category addCategory(Category cat)throws CategoryException;
	
	public Category updateCategory(Category cat)throws CategoryException;
	
	public Category removeCategory(Category cat)throws CategoryException;
	
	public Category viewCategory(Category cat)throws CategoryException;
	
	public List<Category> viewAllCategory()throws CategoryException;
	
	

}
