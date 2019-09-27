package com.giacobbo.blog.factory;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.giacobbo.blog.model.*;

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

	public Post instance() {
		return this.post;
	}
}