package com.giacobbo.blog.builder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.giacobbo.blog.dto.CommentDto;
import com.giacobbo.blog.model.Comment;
import com.giacobbo.blog.model.Post;

public class CommentDtoFactory implements Serializable {

	private static final long serialVersionUID = 1L;

	private CommentDtoFactory() {

	}

	public static CommentDto create(Post post, String content, String author, LocalDateTime data) {
		return new CommentDtoBuilder()
				.addPostId(post.getId())
				.addComment(content)
				.addAuthor(author)
				.addData(data)
				.instance();
	}

	public static List<CommentDto> createList(List<Comment> comments) {
		List<CommentDto> listComments = new ArrayList<CommentDto>();

		if (comments != null)
			for (Comment comment : comments) {
				CommentDto commentDto = new CommentDtoBuilder()
						.addComment(comment.getComment())
						.addAuthor(comment.getAuthor())
						.addData(comment.getCreationDate())
						.instance();
				listComments.add(commentDto);
			}

		return listComments;

	}

}
