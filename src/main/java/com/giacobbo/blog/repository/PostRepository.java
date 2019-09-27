package com.giacobbo.blog.repository;

import org.springframework.data.repository.CrudRepository;

import com.giacobbo.blog.model.Post;

public interface PostRepository extends CrudRepository<Post, String>{
	
}

