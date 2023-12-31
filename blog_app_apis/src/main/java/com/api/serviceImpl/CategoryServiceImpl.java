package com.api.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.CategoryDto;
import com.api.entity.Category;
import com.api.exception.ResourceNotFoundException;
import com.api.repo.CategoryRepo;
import com.api.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		Category cat=this.modelMapper.map(categoryDto, Category.class);
		
	    Category saves=this.categoryRepo.save(cat);
	      
		  return this.modelMapper.map(saves, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		
		Category  category=this.categoryRepo.findById(categoryId).
				orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));
	    
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updated =	this.categoryRepo.save(category);
				
		return this.modelMapper.map(updated, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		
		Category category=this.categoryRepo.findById(categoryId).
	    orElseThrow(()-> new ResourceNotFoundException("Category","categoryId", categoryId));
		
		this.categoryRepo.delete(category);		
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		
		Category category=this.categoryRepo.findById(categoryId).
		orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));
		
	    return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		
		List<Category> categories=this.categoryRepo.findAll();
		
		List<CategoryDto> categoryDtos=categories.stream().map(alllist->this.modelMapper.map(alllist, CategoryDto.class)).collect(Collectors.toList());
		
		return categoryDtos;
	}
	
}
