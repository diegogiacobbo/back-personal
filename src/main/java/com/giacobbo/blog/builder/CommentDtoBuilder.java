package com.giacobbo.blog.builder;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.giacobbo.blog.dto.CommentDto;

public class CommentDtoBuilder implements Serializable {

	private static final long serialVersionUID = 1L;

	private CommentDto commentDto;

	public CommentDtoBuilder () {
		this.commentDto = new CommentDto();
	}
	
	public CommentDtoBuilder addPostId(Long postId) {
		this.commentDto.setPostId(postId);
		return this;
	}

	public CommentDtoBuilder addComment(String content) {
		this.commentDto.setComment(content);
		return this;
	}

	public CommentDtoBuilder addAuthor(String author) {
		this.commentDto.setAuthor(author);
		return this;
	}

	public CommentDtoBuilder addData(LocalDateTime data) {
		this.commentDto.setCreationDate(data);
		return this;
	}

	public CommentDto instance() {
		return this.commentDto;
	}
}
