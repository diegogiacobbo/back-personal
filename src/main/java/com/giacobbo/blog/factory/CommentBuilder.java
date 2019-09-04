package com.giacobbo.blog.factory;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.giacobbo.blog.model.Comment;
import com.giacobbo.blog.model.Post;

public class CommentBuilder implements Serializable {

	private static final long serialVersionUID = 1L;

	private Comment comment;

	public CommentBuilder() {
		this.comment = new Comment();
	}

	public CommentBuilder addPost(Post post) {
	    this.comment.setPost(post);
	    return this;
	}

	public CommentBuilder addContent(String content) {
		this.comment.setComment(content);
		return this;
	}

	public CommentBuilder addAuthor(String author) {
		this.comment.setAuthor(author);
		return this;
	}

	public CommentBuilder addData(LocalDateTime data) {
		this.comment.setCreationDate(data);
		return this;
	}

	public Comment instance() {
		return this.comment;
	}
}
