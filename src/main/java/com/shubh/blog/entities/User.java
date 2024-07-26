package com.shubh.blog.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // used to create a entity
@Table(name = "users") // used to edit table name
@NoArgsConstructor //create a no argument constructor using lonbok
@Getter //create a default getter using lombok
@Setter //create a default setter using lombok
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //automatically generate a id
	private int id;
	
	@Column(name = "user_name", nullable = false, length = 100) //used to specify properties of a perticular column
	private String name;
	
	@Column(name = "user_email", nullable = false, length = 100)
	private String email;
	
	@Column(name = "user_password", nullable = false, length = 100)
	private String password;
	
	@Column(name = "user_about", nullable = true, length = 500)
	private String about;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Post> posts = new ArrayList<>();
}
