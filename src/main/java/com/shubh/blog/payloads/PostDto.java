package com.shubh.blog.payloads;

import java.util.Date; 

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class PostDto {
	
	private Integer postId;
	
	@NotBlank
	@Size(min=4, message = "Post title should be minimum of 4 characters !!")
	private String title;
	
	@NotBlank
	@Size(min=20, message = "Post content should be minimum of 20 characters !!")
	private String content;

	@NotBlank
	@Size(min=4, message = "Post image name should be minimum of 4 characters !!")
	private String imageName;

	@NotBlank
	private Date addedDate;
	
	private CategoryDto category;
	
	private UserDto user;
}
