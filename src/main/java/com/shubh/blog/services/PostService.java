package com.shubh.blog.services;
 

import com.shubh.blog.payloads.PostDto;
import com.shubh.blog.payloads.PostResponse;

public interface PostService {
	
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	PostDto updatePost(PostDto postDto, Integer postId);
	void deletePost(Integer postId);
	PostDto getPostById(Integer postId);
	PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy);
	PostResponse getAllPostsByCategory(Integer categoryId, Integer pageNumber, Integer pageSize, String sortBy);
	PostResponse getAllPostsByUser(Integer userId, Integer pageNumber, Integer pageSize, String sortBy);
	PostResponse searchPost(String keyword, Integer pageNumber, Integer pageSize, String sortBy);
}
