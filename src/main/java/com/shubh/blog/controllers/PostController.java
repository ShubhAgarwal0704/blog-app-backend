package com.shubh.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.shubh.blog.payloads.ApiResponse;
import com.shubh.blog.payloads.PostDto;
import com.shubh.blog.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@PostMapping("/user/{userId}/category/{categoryId}/post")
	public ResponseEntity<PostDto> createPost(
			@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId){
		
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		
		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUserId(@PathVariable Integer userId){
		List<PostDto> postByUser = this.postService.getAllPostsByUser(userId);
		return ResponseEntity.ok(postByUser);
	}
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategoryId(@PathVariable Integer categoryId){
		List<PostDto> postByCategory = this.postService.getAllPostsByCategory(categoryId);
		return ResponseEntity.ok(postByCategory);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber, //by default page starts from 0
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize
			){
		List<PostDto> allPosts = this.postService.getAllPosts(pageNumber, pageSize);
		return ResponseEntity.ok(allPosts);
	}
	
	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		PostDto post = this.postService.getPostById(postId);
		return ResponseEntity.ok(post);
	}
	
	@DeleteMapping("/post/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted successfully!!", true),HttpStatus.OK);
	}
	
	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> updatePost(
			@RequestBody PostDto postDto,
			@PathVariable Integer postId){
		PostDto updatedPost = this.postService.updatePost(postDto, postId);
		return ResponseEntity.ok(updatedPost);
		
	}

	
}
