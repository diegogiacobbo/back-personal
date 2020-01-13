package com.giacobbo.blog.service;

import java.util.List;

import com.giacobbo.blog.dto.PostDtoResponse;
import com.giacobbo.blog.model.Post;

public interface PostService {

	public 
	PostDtoResponse getPost(String id);

	public String postAdd(Post post);

	public List<PostDtoResponse> findAll();
	
	public List<PostDtoResponse> findPublicPosts();
	
	public PostDtoResponse findLastPost();

}
