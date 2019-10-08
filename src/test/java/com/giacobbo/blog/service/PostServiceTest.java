package com.giacobbo.blog.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.giacobbo.blog.factory.PostFactory;
import com.giacobbo.blog.model.Post;
import com.giacobbo.blog.repository.PostRepositoryImpl;

@RunWith(SpringRunner.class)
public class PostServiceTest extends AbstractServiceTest {

	@Autowired
	private PostServiceImpl postService;

	@Autowired
	private PostRepositoryImpl postRepository;
	
	private LocalDateTime local = LocalDateTime.now();

	private Post post;
	
	@Before
	public void contextTests() {
		this.post = createPostTest(local);
	}
	
	@Test
	public void shouldReturnAllPost() {
		assertNotNull(postRepository.findAll());
	}

	@Test
	public void shouldReturnCreatedPost() {
		assertNotNull("Post shouldn't be null", post);
		assertThat(post.getContent(), equalTo("Test content"));
		assertThat(post.getTitle(), equalTo("Test title"));
		assertThat(post.getCreationDate(), equalTo(local));
	}

	@Test
	public void shouldReturnNullForNotExistingPost() {
		assertNotNull(postService.getPost(post.getId().toString()));
	}
	
	public Post createPostTest(LocalDateTime local) {
		return postRepository.save(PostFactory.create("Test title", "Test content", local));
	}
}