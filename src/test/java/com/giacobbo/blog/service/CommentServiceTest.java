package com.giacobbo.blog.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.giacobbo.blog.dto.CommentDto;
import com.giacobbo.blog.factory.CommentDtoBuilder;
import com.giacobbo.blog.factory.CommentDtoFactory;
import com.giacobbo.blog.factory.PostFactory;
import com.giacobbo.blog.model.Post;
import com.giacobbo.blog.repository.PostRepositoryImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceTest {

	@Autowired
	PostRepositoryImpl postRepository;

	@Autowired
	CommentServiceImpl commentService;

	private Post createTestPost() {
		return postRepository.save(PostFactory.create("Test content", "Test title", LocalDateTime.now()));
	}
	
	@Test
	public void shouldAddComment() {
		Post post = createTestPost();
		String commentId = commentService.addComment(CommentDtoFactory.create(post, "Test content", "Author", LocalDateTime.now()));

		assertThat("Comment id shouldn't be null", commentId, notNullValue());
	}

	@Test
	public void shouldReturnAddedComment() {
		Post post = createTestPost();

		CommentDtoBuilder commentBuilder = new CommentDtoBuilder();
		CommentDto commentDto = commentBuilder.addPostId(post.getId()).addAuthor("Author").addComment("Content").addData(LocalDateTime.now())
				.instance();
		commentService.addComment(commentDto);
		List<CommentDto> comments = commentService.getCommentsForPost(post.getId().toString());

		assertThat("There should be one comment", comments, hasSize(1));
		assertThat(comments.get(0).getAuthor(), equalTo("Author"));
		assertThat(comments.get(0).getComment(), equalTo("Content"));
	}
}