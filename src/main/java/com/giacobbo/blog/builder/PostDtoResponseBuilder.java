package com.giacobbo.blog.builder;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.giacobbo.blog.dto.PostDtoResponse;

public class PostDtoResponseBuilder implements Serializable {

	private static final long serialVersionUID = 1L;

	private PostDtoResponse postDtoResponse;

	public PostDtoResponseBuilder() {
		this.postDtoResponse = new PostDtoResponse();
	}

	public PostDtoResponseBuilder addContent(String content) {
		this.postDtoResponse.setContent(content);
		return this;
	}

	public PostDtoResponseBuilder addTitle(String title) {
		this.postDtoResponse.setTitle(title);
		return this;
	}

	public PostDtoResponseBuilder addData(LocalDateTime data) {
		this.postDtoResponse.setCreationDate(data);
		return this;
	}

	public PostDtoResponseBuilder addPublic(Boolean ispublic) {
		this.postDtoResponse.setIspublic(ispublic);
		return this;
	}

	public PostDtoResponseBuilder addCode(String code) {
		this.postDtoResponse.setCode(code);
		return this;
	}

	public PostDtoResponseBuilder addImage(String image) {
		this.postDtoResponse.setImage(image);
		return this;
	}

	public PostDtoResponse instance() {
		return this.postDtoResponse;
	}

	public PostDtoResponseBuilder addId(Long id) {
		this.postDtoResponse.setId(id);
		return this;
	}
}