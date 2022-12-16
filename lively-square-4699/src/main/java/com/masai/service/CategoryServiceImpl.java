package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.CategoryException;
import com.masai.model.Category;
import com.masai.repository.CategoryDao;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryDao catgegoryDao;

	@Override
	public Category addCategory(Category cat) throws CategoryException{
		// TODO Auto-generated method stub
		
		Category category  =catgegoryDao.save(cat);
		
		if(category!=null) {
			return category;
		}
		else {
			throw new CategoryException("Category not add.....");
		}
		
		
	}

	@Override
	public Category updateCategory(Category cat) throws CategoryException{
		// TODO Auto-generated method stub
		
		Optional<Category> category =catgegoryDao.findById(cat.getCategoryId());
		
		if(category.isPresent()) {
			return catgegoryDao.save(cat);
		}
		else {
			throw new CategoryException("category not found....");
		}
	}

	@Override
	public Category removeCategory(Category cat) throws CategoryException{
		// TODO Auto-generated method stub
		Optional<Category> category =catgegoryDao.findById(cat.getCategoryId());
		
		if(category.isPresent()) {
			 catgegoryDao.delete(cat);
			 
			 return cat;

		}
		else {
			throw new CategoryException("category not found....");
		}
//		return null;
	}

	@Override
	public Category viewCategory(Category cat) throws CategoryException{
		// TODO Auto-generated method stub
		
		Optional<Category> category =catgegoryDao.findById(cat.getCategoryId());
		
		if(category.isPresent()) {
			return category.get();
		}
		else {
			throw new CategoryException("category not found....");
		}
		
		
	}

	@Override
	public List<Category> viewAllCategory() throws CategoryException{
		// TODO Auto-generated method stub
		List<Category> lists=catgegoryDao.findAll();
		
		
		if(lists.size()>0) {
			return lists;
		}
		else {
			throw new CategoryException("Category is empty.....");
		}
	}

	

}
