package com.nichi.ecommerce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nichi.ecommerce.common.ApiResponse;
import com.nichi.ecommerce.model.Category;
import com.nichi.ecommerce.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	private CategoryService categoryService;
	
	
	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}

	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category) {
		categoryService.createCategory(category);
		return new ResponseEntity<>(new ApiResponse(true, "new category created"),HttpStatus.CREATED);
	}
	
	@GetMapping("/list")
	public List<Category> listCategory() {
		return categoryService.listcategory();	 
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<ApiResponse> updateCategory(@PathVariable("id") int id, @RequestBody Category category) {
		System.out.println("Category id "+id);
		
		if(!categoryService.findById(id)){
			return new ResponseEntity<>(new ApiResponse(false, "category does not exist"),HttpStatus.NOT_FOUND);
		}
		
		categoryService.editCategory(id, category);
		return new ResponseEntity<>(new ApiResponse(true, "Category has been updated"),HttpStatus.OK);
	}
}
