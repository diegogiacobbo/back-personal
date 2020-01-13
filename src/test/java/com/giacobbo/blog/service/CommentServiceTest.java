package com.giacobbo.blog.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.giacobbo.blog.builder.CommentDtoBuilder;
import com.giacobbo.blog.builder.CommentDtoFactory;
import com.giacobbo.blog.builder.PostFactory;
import com.giacobbo.blog.dto.CommentDto;
import com.giacobbo.blog.model.Post;
import com.giacobbo.blog.repository.PostRepository;

@RunWith(SpringRunner.class)
public class CommentServiceTest extends AbstractServiceTest {

	@Autowired
	PostRepository postRepository;

	@Autowired
	CommentService commentService;

	Post post = new Post();

	String commentId = new String();

	private Post createTestPost() {
		return postRepository.save(PostFactory.create("Test content", "Test title", LocalDateTime.now(), "hello word image", "hello word code"));
	}

	@Before
	public void contextTests() {
		post = createTestPost();
	}

	@Test
	public void shouldAddComment() {
		assertNotNull(commentService
				.addComment(CommentDtoFactory.create(post, "Test content", "Author", LocalDateTime.now())));
	}

	@Test
	public void shouldGetComment() {
		assertThat("Comment id shouldn't be null", commentId, notNullValue());
	}

	@Test
	public void shouldReturnAddedComment() {
		Post post = createTestPost();

		CommentDtoBuilder commentBuilder = new CommentDtoBuilder();
		CommentDto commentDto = commentBuilder.addPostId(post.getId()).addAuthor("Author").addComment("Content")
				.addData(LocalDateTime.now()).instance();
		commentService.addComment(commentDto);
		List<CommentDto> comments = commentService.getCommentsForPost(post.getId().toString());

		assertThat("There should be one comment", comments, hasSize(1));
		assertThat(comments.get(0).getAuthor(), equalTo("Author"));
		assertThat(comments.get(0).getComment(), equalTo("Content"));
	}
}