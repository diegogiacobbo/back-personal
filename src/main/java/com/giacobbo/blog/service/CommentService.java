package com.giacobbo.blog.service;

import java.util.List;

import com.giacobbo.blog.dto.CommentDto;

public interface CommentService {

	/**
	 * Returns a list of all comments for a blog post with passed id.
	 *
	 * @param postId id of the post
	 * @return list of comments sorted by creation date descending - most recent
	 *         first
	 *
	 * @throws IllegalArgumentException if there is no blog post for passed postId
	 */
	public List<CommentDto> getCommentsForPost(String postId);

	/**
	 * Creates a new comment
	 *
	 * @param CommentDto data of new comment
	 * @return id of the created comment
	 *
	 * @throws IllegalArgumentException if there is no blog post for passed
	 *                                  newCommentDto.postId
	 */
	public String addComment(CommentDto commentDto);
}