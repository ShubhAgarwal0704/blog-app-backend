package com.shubh.blog.services;

import java.util.List;

import com.shubh.blog.payloads.CategoryDto;

public interface CategoryService {
	
	//create
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	//delete
	void deleteCategory(Integer categoryId);
	
	//get
	CategoryDto getCategoryById(Integer categoryId);
	
	//get all
	List<CategoryDto> getCategories();

}
