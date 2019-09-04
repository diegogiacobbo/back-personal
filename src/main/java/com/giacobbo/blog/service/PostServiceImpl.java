package com.giacobbo.blog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giacobbo.blog.dto.PostDto;
import com.giacobbo.blog.model.Post;
import com.giacobbo.blog.repository.PostRepositoryImpl;

@Service
public class PostServiceImpl {

	@Autowired
	private PostRepositoryImpl postRepository;

	public PostDto getPost(String id) {

		Optional<Post> post3 = postRepository.findById(id);

		return postRepository.findById(id)
				.map(post -> new PostDto(post3.get().getTitle(), 
						post3.get().getContent(), 
						post3.get().getCreationDate())).
				orElse(null);
	}

	public String postAdd(Post post) {
		return postRepository.save(post).getId().toString();
	}

	public List<Post> findAll() {
		List<Post> cities = (List<Post>) postRepository.findAll();
		return cities;
	}

}
