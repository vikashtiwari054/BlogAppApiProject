package com.api.service;

import java.util.List;

import com.api.dto.PostDto;
import com.api.entity.Category;
import com.api.entity.Post;

public interface PostService {

	//create //
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	// update //
	PostDto updatePost(PostDto postDto,Integer postId);
	
	
	//delete
	void deletePost(Integer postId);
	
	// get all post
	List<PostDto> getAllPost(Integer pageNumber,Integer pageSize);
	
	// get single post
	PostDto getSinglePostById(Integer postId);
	
	// get all post by category
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	
	// get all post by user
	List<PostDto> getPostsByUser(Integer userId);
	
	// search posts
	List<PostDto> searchPosts(String keyword);
	
	
}
