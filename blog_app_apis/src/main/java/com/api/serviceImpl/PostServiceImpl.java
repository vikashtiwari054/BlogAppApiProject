package com.api.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.CategoryDto;
import com.api.dto.PostDto;
import com.api.entity.Category;
import com.api.entity.Post;
import com.api.entity.User;
import com.api.exception.ResourceNotFoundException;
import com.api.repo.CategoryRepo;
import com.api.repo.PostRepo;
import com.api.repo.UserRepo;
import com.api.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		
    User user=this.userRepo.findById(userId).orElseThrow(()-> new  ResourceNotFoundException("User","User id",userId));		
	
    Category category=this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
    
	Post post=this.modelMapper.map(postDto, Post.class);
		 post.setImageName("default.png"); 
	     post.setAddedDate(new Date(System.currentTimeMillis()));
	     post.setUser(user);
	     post.setCategory(category);
	     
	     Post newPost=this.postRepo.save(post);
	
	     return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
	
		Post post=this.postRepo.updatePostByPostId(postId).
		orElseThrow(()->new ResourceNotFoundException("post", "post id", postId));
		
	   	post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post updatedPost=this.postRepo.save(post);
		
		return this.modelMapper.map(updatedPost, PostDto.class);
	}
	@Override
	public void deletePost(Integer postId) {
		
		Post post=this.postRepo.findById(postId)
		.orElseThrow(()-> new ResourceNotFoundException("post","post id", postId));
		
		this.postRepo.delete(post);
		
	}
	@Override
	public List<PostDto> getAllPost(Integer pageNumber,Integer pageSize) {
		
		List<Post> allposts=this.postRepo.findAll();
		List<PostDto> postDtos=allposts.stream().map((t->this.modelMapper.map(t, PostDto.class))).collect(Collectors.toList());
		
		return postDtos;
	}
	@Override
	public PostDto getSinglePostById(Integer postId) {
		
        Post  post=this.postRepo.findById(postId)
        .orElseThrow(()->new ResourceNotFoundException("Post","postId", postId));
        
        return this.modelMapper.map(post, PostDto.class);
        	
	}
	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "category id", categoryId));
		List<Post> posts=this.postRepo.findByCategory(category);
		
	    List<PostDto>  postDtos	=posts.stream().map((post-> this.modelMapper.map(post, PostDto.class))).collect(Collectors.toList());
		
		return postDtos;
	}
	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "user id", userId));
		List<Post> posts=this.postRepo.findByUser(user);
		
		List<PostDto> postDtos=posts.stream().map((postToUser-> this.modelMapper.map(postToUser, PostDto.class))).collect(Collectors.toList());
		
		return postDtos;
	}
	@Override
	public List<PostDto> searchPosts(String keyword) {
		
		List<Post>  posts=this.postRepo.searchPostByTitle("%"+keyword+"%");
		
		List<PostDto> postDtos=posts.stream().map(title->this.modelMapper.map(title, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}
}
