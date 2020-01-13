package com.giacobbo.blog.dto;

import java.time.LocalDateTime;

public class PostDtoResponse {

	private Long id;
	
	private String title;

	private String content;

	private LocalDateTime creationDate;
	
	private String image;
	
	private String code;
	
	private Boolean ispublic;

	public PostDtoResponse() {
		super();
	}

	public PostDtoResponse(Long id, String title, String content, LocalDateTime creationDate, String base64, String code) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.creationDate = creationDate;
		this.image = base64;
		this.code = code;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Boolean getIspublic() {
		return ispublic;
	}

	public void setIspublic(Boolean ispublic) {
		this.ispublic = ispublic;
	}
	
}
