package com.giacobbo.blog.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.giacobbo.blog.model.Comment;

public interface CommentRepository extends CrudRepository<Comment, String> {

	List<Comment> findByPost(@Param("post") String postId);

}
