package com.shubh.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shubh.blog.repositories.UserRepo;

@SpringBootTest
class BlogAppApplicationTests {
	
	@Autowired
	private UserRepo userRepo;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void userRepoTest() {
		String className = this.userRepo.getClass().getName();
		String packName = this.userRepo.getClass().getPackageName();
		System.out.println(className);  //a class is created by spring dynamically at run time
		System.out.println(packName); //inside the package jdk.proxy2
	}

}
