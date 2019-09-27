package com.giacobbo.blog.dto;

import java.time.LocalDateTime;

public class CommentDto {

	private Long id;

	private String comment;

	private String author;
	
	private Long postId;

	private LocalDateTime creationDate;

	public CommentDto(Long id, String comment, String author, LocalDateTime creationDate) {
		super();
		this.id = id;
		this.comment = comment;
		this.author = author;
		this.creationDate = creationDate;
	}
	
	public CommentDto(Long postId, String comment, String author) {
		super();
		this.comment = comment;
		this.author = author;
		this.postId = postId;
	}

	public CommentDto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public String getComment() {
		return comment;
	}

	public String getAuthor() {
		return author;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}
	
}
