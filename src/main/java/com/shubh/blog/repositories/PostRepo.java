package com.shubh.blog.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shubh.blog.entities.Category;
import com.shubh.blog.entities.Post;
import com.shubh.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{

	Page<Post> findByUser(User user, Pageable pObj);
	
	Page<Post> findByCategory(Category category, Pageable pObj);
	
	@Query("SELECT p FROM Post p WHERE p.title LIKE %:keyword%")  // search functionality is because of this
	Page<Post> searchByTitle(@Param("keyword") String keyword, Pageable pObj);
}
