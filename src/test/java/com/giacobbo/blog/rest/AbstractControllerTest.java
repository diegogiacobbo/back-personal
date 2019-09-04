package com.giacobbo.blog.rest;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.giacobbo.blog.service.CommentServiceImpl;
import com.giacobbo.blog.service.PostServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest
@NoRepositoryBean
public abstract class AbstractControllerTest {

	@Autowired
	protected MockMvc mockMvc;

	@MockBean
	protected PostServiceImpl postService;

	@MockBean
	protected CommentServiceImpl commentService;

	@Before
	public void setUp() {
		Mockito.reset(postService, commentService);
	}

}
