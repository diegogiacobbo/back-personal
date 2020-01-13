package com.giacobbo.blog.rest;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.giacobbo.blog.service.CommentService;
import com.giacobbo.blog.service.PostService;

@RunWith(SpringRunner.class)
@ContextConfiguration 
@WebMvcTest
public abstract class AbstractControllerTest {

	@Autowired
	protected MockMvc mockMvc;

	@MockBean
	protected PostService postService;

	@MockBean
	protected CommentService commentService;
	
    @Autowired
    private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		Mockito.reset(postService, commentService);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

}
