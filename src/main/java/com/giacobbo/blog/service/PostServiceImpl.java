package com.giacobbo.blog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giacobbo.blog.builder.PostDtoResponseFactory;
import com.giacobbo.blog.dto.PostDtoResponse;
import com.giacobbo.blog.model.Post;
import com.giacobbo.blog.repository.PostRepositoryImpl;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepositoryImpl postRepository;
	
	@Override
	public PostDtoResponse getPost(String id) {

		Optional<Post> postOptional = postRepository.findById(id);
		
		if(postOptional.isPresent()){
		    return PostDtoResponseFactory.create(
					postOptional.get().getId(),
					postOptional.get().getTitle(), 
					postOptional.get().getContent(), 
					postOptional.get().getCreationDate(),
					postOptional.get().getImage(),
					postOptional.get().getContent_code(),
					postOptional.get().getIsPublic());
		}
		    
		return null;
		
	}

	@Override
	public String postAdd(Post post) {
		return postRepository.save(post).getId().toString();
	}

	@Override
	public List<PostDtoResponse> findAll() {
		List<PostDtoResponse> listPostDto = new ArrayList<PostDtoResponse>();
		Iterable<Post> optionalPosts = postRepository.findAll();
		optionalPosts.forEach(post -> listPostDto.add(
				PostDtoResponseFactory.create(
						post.getId(),
						post.getTitle(), 
						post.getContent(), 
						post.getCreationDate(),
						post.getImage(),
						post.getContent_code(),
						post.getIsPublic())));
		
		return listPostDto;
	}

	@Override
	public List<PostDtoResponse> findPublicPosts() {

		List<PostDtoResponse> listPostDtoResponse = new ArrayList<PostDtoResponse>();

		postRepository.findPublicPosts().forEach(post -> {
			listPostDtoResponse.add(
				PostDtoResponseFactory.create(
					post.getId(),
					post.getTitle(), 
					post.getContent(), 
					post.getCreationDate(),
					post.getImage(),
					post.getContent_code(),
					post.getIsPublic()
				)
			);
		});

		return listPostDtoResponse;
	}

	@Override
	public PostDtoResponse findLastPost() {
		Post post = postRepository.findTopByReverseOrderByCreationDate();
		
		PostDtoResponse postDtoResponse = PostDtoResponseFactory.create(
						post.getId(),
						post.getTitle(), 
						post.getContent(), 
						post.getCreationDate(),
						post.getImage(),
						post.getContent_code(),
						post.getIsPublic());
		
		return postDtoResponse;
	}
}
