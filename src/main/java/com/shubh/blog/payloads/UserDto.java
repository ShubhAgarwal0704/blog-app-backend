package com.shubh.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

//payload is created to show the fields which will be actually exposed
//whereas in entities same details are present but they are specific to database table
public class UserDto {
	
	private int id;
	
	@NotEmpty
	@Size(min=4, message = "Username should be minimum of 4 characters !!")
	private String name;
	
	@Email(message = "Invalid email !!")
	private String email;
	
	@NotEmpty
	@Size(min=5, message = "password should be at least 5 character long !!")
	private String password;
	
	@NotEmpty
	private String about;

}
