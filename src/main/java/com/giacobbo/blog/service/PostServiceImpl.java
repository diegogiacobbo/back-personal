package com.giacobbo.blog.service;

import java.util.ArrayList;
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

		Optional<Post> postOptional = postRepository.findById(id);

		return postRepository.findById(id).map(post -> new PostDto(postOptional.get().getId(),
				postOptional.get().getTitle(), postOptional.get().getContent(), postOptional.get().getCreationDate()))
				.orElse(null);
	}

	public String postAdd(Post post) {
		return postRepository.save(post).getId().toString();
	}

	public List<Post> findAll() {
		List<Post> posts = (List<Post>) postRepository.findAll();
		return posts;
	}

	public List<PostDto> findPublicPosts() {

		List<PostDto> listPostDto = new ArrayList<PostDto>();

		postRepository.findPublicPosts().forEach(post -> {
			listPostDto.add(new PostDto(post.getId(), post.getTitle(), post.getContent(), post.getCreationDate()));
		});

		return listPostDto;
	}
}
