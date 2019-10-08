package com.giacobbo.blog.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.giacobbo.blog.model.Post;

public interface PostRepository extends CrudRepository<Post, String>{
	
	public Optional<Post> findById(String id);
	
	public Post findTopByReverseOrderByCreationDate();
	
	public Iterable<Post> findPublicPosts();
	
}

