package com.giacobbo.blog.service;

import java.util.List;

import com.giacobbo.blog.dto.PostDto;
import com.giacobbo.blog.model.Post;

public interface PostService {

	public PostDto getPost(String id);

	public String postAdd(Post post);

	public List<Post> findAll();

}
