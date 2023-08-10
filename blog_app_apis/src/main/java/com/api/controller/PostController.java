package com.api.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import  org.springframework.http.MediaType;
import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.dto.ApiResponse;
import com.api.dto.PostDto;
import com.api.service.FileService;
import com.api.service.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService  fileService;
	
	@Value("${project.image}")
	private String path;
	
	
	//create 
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
			@PathVariable Integer userId,@PathVariable Integer categoryId){
		
		PostDto createPost=this.postService.createPost(postDto, userId, categoryId);
		
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);	
	}
	// get by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
		
		List<PostDto> postDtos=this.postService.getPostsByUser(userId);
		
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
	}
	// get  by category
	@GetMapping("/category/{categoryId}/categories")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
		
		List<PostDto> postDtos=this.postService.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
	}
	// get all post
	@GetMapping("/allpost")
	public ResponseEntity<List<PostDto>> getAllPost(
			@RequestParam(value="pageNumber" ,defaultValue ="1",required = false)Integer pageNumber,
			@RequestParam(value ="pageSize",defaultValue ="1",required = false) Integer pageSize
			){
		
		List<PostDto> postDtos=this.postService.getAllPost(pageNumber, pageSize);
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
	}
	// get post detail by id
	@GetMapping("/{postId}")
	public ResponseEntity<PostDto> getSinglePostById(@PathVariable Integer postId) {
		
	 PostDto  postDto=this.postService.getSinglePostById(postId); 
	 return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
	}
	//delete posts
	@DeleteMapping("/{postId}")
	public ApiResponse deletePost(@PathVariable Integer PostId)
	{
		this.postService.deletePost(PostId);
		return new ApiResponse("Post is successfully deleted... !!",true);
	}
	//update post
	@PostMapping("posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable("postId") Integer postId){
		
		PostDto updatePostDto2=this.postService.updatePost(postDto, postId);
		
		return new ResponseEntity<PostDto>(updatePostDto2,HttpStatus.OK);
		
	}
	@PostMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchByTitle(@PathVariable("keyword") String keyword){
		
		
		List<PostDto> list=this.postService.searchPosts(keyword);
			
		return new ResponseEntity<>(list,HttpStatus.OK);	
	}
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto>  uploadPostImage(@RequestParam("image") MultipartFile  image,
			@PathVariable Integer postId) throws IOException
	{
		PostDto   postDto=this.postService.getSinglePostById(postId);
		
		String fileName=this.fileService.uploadImage(path, image);
		postDto.setImageName(fileName);
		
		PostDto   updatePost=this.postService.updatePost(postDto, postId);
	          
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);	
	}
	@GetMapping(value ="/post/image/{imageName}",produces =MediaType.ALL_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName,HttpServletResponse httpServletResponse) throws IOException
	{
	       
			InputStream inputStream=this.fileService.getResource(path, imageName);
			httpServletResponse.setContentType(MediaType.ALL_VALUE);
			StreamUtils.copy(inputStream, httpServletResponse.getOutputStream());
		}	
	       
	}
		

