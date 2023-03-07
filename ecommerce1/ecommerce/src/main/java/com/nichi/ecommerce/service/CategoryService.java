package com.nichi.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nichi.ecommerce.model.Category;
import com.nichi.ecommerce.repository.CategoryRepository;

@Service
public class CategoryService {

	private CategoryRepository categoryRepository;
	
	public CategoryService(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}

	public void createCategory(Category category) {
		categoryRepository.save(category);
	}
	
	public List<Category> listcategory(){
		return categoryRepository.findAll();
	}
}
