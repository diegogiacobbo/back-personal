package com.giacobbo.blog.dto;

import java.time.LocalDateTime;

public class PostDto {

	private Long id;
	
	private String title;

	private String content;

	private LocalDateTime creationDate;

	public PostDto(Long id, String title, String content, LocalDateTime creationDate) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.creationDate = creationDate;
	}
	
	public Long getId() {
		return this.id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}
}
