package com.giacobbo.blog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giacobbo.blog.builder.CommentDtoFactory;
import com.giacobbo.blog.builder.CommentFactory;
import com.giacobbo.blog.dto.CommentDto;
import com.giacobbo.blog.model.Comment;
import com.giacobbo.blog.model.Post;
import com.giacobbo.blog.repository.CommentRepositoryImpl;
import com.giacobbo.blog.repository.PostRepositoryImpl;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepositoryImpl postRepository;

	@Autowired
	private CommentRepositoryImpl commentRepository;

	@Override
	public List<CommentDto> getCommentsForPost(String postId) {
		List<Comment> comments = commentRepository.findByPost(postId).stream().collect(Collectors.toList());
		CommentDtoFactory.createList(comments);
		return CommentDtoFactory.createList(comments);
	}

	@Override
	public String addComment(CommentDto commentDto) {
		Post p = postRepository.findById(commentDto.getPostId().toString()).get();
		Comment c = commentRepository.save(CommentFactory.create(p, commentDto.getComment(), commentDto.getAuthor(),
				commentDto.getCreationDate()));
		return c.getId().toString();
	}

}
