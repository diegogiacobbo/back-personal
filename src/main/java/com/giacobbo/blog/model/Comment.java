package com.giacobbo.blog.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.persistence.Id;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "comment_post")
@EntityListeners(AuditingEntityListener.class)
public class Comment {

	@Id
	@Column(name = "id")
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "comment_post")
	private String comment;

	@Column(name = "creation")
	private LocalDateTime creationDate;

	@Column(name = "author")
	private String author;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "post_id", nullable = false)
	private Post post;

	public String getComment() {
		return comment;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public String getAuthor() {
		return author;
	}

	public void setComment(String content) {
		this.comment = content;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
