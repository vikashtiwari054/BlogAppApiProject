package com.api.repo;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.dto.UserDto;
import com.api.entity.Category;
import com.api.entity.Post;
import com.api.entity.User;

public interface PostRepo extends JpaRepository<Post, Integer>{

	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
	@Query(value = "select p.post_Id,p.category_id,p.user_id,p.content,p.post_title,p.image_name,p.added_date from posts p where p.post_Id=:postId",nativeQuery = true)
    Optional<Post>  updatePostByPostId(@Param("postId")  Integer postId); 
	
	@Query(value ="select * from posts p where p.post_title like :keyword",nativeQuery = true)
	List<Post> searchPostByTitle(@Param("keyword") String title);
	
//	@Transactional
//	@Modifying
//	@Query(value = "select  p.post_Id,p.content,p.post_title,p.image_name,p.added_date from posts p where p.added_date>=:startdate and p.added_date<=:enddate ",nativeQuery = true)
//	List<Post>  findAllPosts(@Param("startdate") LocalDateTime startdate ,@Param("enddate") LocalDateTime enddate);
	
	
	
}
