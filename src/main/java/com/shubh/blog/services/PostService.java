package com.shubh.blog.services;

import java.util.List; 

import com.shubh.blog.payloads.PostDto;

public interface PostService {
	
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	PostDto updatePost(PostDto postDto, Integer postId);
	void deletePost(Integer postId);
	PostDto getPostById(Integer postId);
	List<PostDto> getAllPosts(Integer pageNumber, Integer pageSize);
	List<PostDto> getAllPostsByCategory(Integer categoryId);
	List<PostDto> getAllPostsByUser(Integer userId);
	List<PostDto> searchPost(String keyword);
}
