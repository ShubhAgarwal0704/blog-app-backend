package com.shubh.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.shubh.blog.entities.Category;
import com.shubh.blog.entities.Post;
import com.shubh.blog.entities.User;
import com.shubh.blog.exceptions.ResourceNotFoundException;
import com.shubh.blog.payloads.PostDto;
import com.shubh.blog.payloads.PostResponse;
import com.shubh.blog.repositories.CategoryRepo;
import com.shubh.blog.repositories.PostRepo;
import com.shubh.blog.repositories.UserRepo;
import com.shubh.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setAddedDate(new Date());
		post.setImageName("default.png");
		post.setCategory(this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId)));
		post.setUser(this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId)));

		Post newPost = this.postRepo.save(post);

		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());

		Post updatedPost = this.postRepo.save(post);

		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));

		this.postRepo.delete(post);
	}

	@Override
	public PostDto getPostById(Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));

		PostDto postDto = this.modelMapper.map(post, PostDto.class);
		return postDto;
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy) {

		Pageable pObj = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
		Page<Post> pagePost = this.postRepo.findAll(pObj);

		List<Post> posts = pagePost.getContent();

		List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();

		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pageNumber);
		postResponse.setPageSize(pageSize);
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPage(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());

		return postResponse;
	}

	@Override
	public PostResponse getAllPostsByCategory(Integer categoryId, Integer pageNumber, Integer pageSize, String sortBy) {

		Pageable pObj = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
		Page<Post> pagePost = this.postRepo.findByCategory(category, pObj);
		List<Post> posts = pagePost.getContent();

		List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();

		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pageNumber);
		postResponse.setPageSize(pageSize);
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPage(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());

		return postResponse;
	}

	@Override
	public PostResponse getAllPostsByUser(Integer userId, Integer pageNumber, Integer pageSize, String sortBy) {
		Pageable pObj = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));
		Page<Post> pagePost = this.postRepo.findByUser(user, pObj);
		List<Post> posts = pagePost.getContent();

		List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();

		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pageNumber);
		postResponse.setPageSize(pageSize);
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPage(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());

		return postResponse;
	}

	@Override
	public PostResponse searchPost(String keyword, Integer pageNumber, Integer pageSize, String sortBy) {
		Pageable pObj = PageRequest.of(pageNumber, pageSize);
		Page<Post> pagePost = this.postRepo.searchByTitle(keyword, pObj);

		List<Post> posts = pagePost.getContent();

		List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();

		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pageNumber);
		postResponse.setPageSize(pageSize);
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPage(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());

		return postResponse;
	}

}
