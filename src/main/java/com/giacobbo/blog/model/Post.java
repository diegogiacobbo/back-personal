package com.giacobbo.blog.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
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
	
	@Column(columnDefinition = "TEXT", name = "content_post")
	private String content;

	@Column(name = "creation")
	private LocalDateTime creationDate;
	
	@Column(name = "is_public")
	private Boolean isPublic;
	
	@Column(columnDefinition = "TEXT", name = "content_code")
	private String content_code;
	
	@Lob
	@Column(name = "image")
	@Type(type="org.hibernate.type.BinaryType")
	private byte[] image;
	
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

	public Boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	public String getContent_code() {
		return content_code;
	}

	public void setContent_code(String content_code) {
		this.content_code = content_code;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}

