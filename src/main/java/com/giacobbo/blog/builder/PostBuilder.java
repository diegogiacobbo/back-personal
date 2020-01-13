package com.giacobbo.blog.builder;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.giacobbo.blog.model.Post;

public class PostBuilder implements Serializable {

	private static final long serialVersionUID = 1L;

	private Post post;

	public PostBuilder() {
		this.post = new Post();
	}

	public PostBuilder addContent(String content) {
		this.post.setContent(content);
		return this;
	}

	public PostBuilder addTitle(String title) {
		this.post.setTitle(title);
		return this;
	}

	public PostBuilder addData(LocalDateTime data) {
		this.post.setCreationDate(data);
		return this;
	}

	public PostBuilder addPublic(Boolean ispublic) {
		this.post.setIsPublic(ispublic);
		return this;
	}

	public PostBuilder addCode(String code) {
		this.post.setContent_code(code);
		return this;
	}

	public PostBuilder addImage(byte[] image) {
		this.post.setImage(image);
		return this;
	}

	public Post instance() {
		return this.post;
	}

	public PostBuilder addId(Long id) {
		this.post.setId(id);
		return this;
	}
}