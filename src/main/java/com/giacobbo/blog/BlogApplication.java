package com.giacobbo.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(scanBasePackages = { "com.giacobbo.blog.repository", 
		"com.giacobbo.blog.service", "com.giacobbo.blog.rest", 
		"com.giacobbo.blog.model", "com.giacobbo.blog"})
@Configuration
@EntityScan(basePackages = { "com.giacobbo.blog.model" })
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}
	
}

