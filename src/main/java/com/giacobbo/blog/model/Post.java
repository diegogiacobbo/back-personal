package com.giacobbo.blog.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "post")
@EntityListeners(AuditingEntityListener.class)
public class Post {

	@Id
    @Column(name = "id")
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;

    @Column(name = "title")
	private String title;
	
	@Column(name = "content_post")
	private String content;

	@Column(name = "creation")
	private LocalDateTime creationDate;
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}

