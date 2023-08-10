package com.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

	
	
}
