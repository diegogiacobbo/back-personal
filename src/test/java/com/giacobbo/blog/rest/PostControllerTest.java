package com.giacobbo.blog.rest;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.json.JSONObject;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.test.context.ContextConfiguration;

import com.giacobbo.blog.dto.PostDto;
import com.giacobbo.blog.factory.PostFactory;
import com.giacobbo.blog.model.Post;

@ContextConfiguration(classes = PostController.class)
public class PostControllerTest extends AbstractControllerTest {

	private static final String TOKEN_ATTR_NAME = "org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN";

	@Test
	@WithMockUser(username = "user", password = "password", roles = "USER")
	public void shouldReturnFoundPost() throws Exception {

		// given
		LocalDateTime creationDate = LocalDateTime.of(2018, 5, 20, 20, 51, 16);
		PostDto post = new PostDto(1L, "Title", "content", creationDate);

		// when
		when(postService.getPost("1")).thenReturn(post);

		// then
		mockMvc.perform(get("/posts/1").accept(APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_UTF8)).andExpect(jsonPath("$.title", is("Title")))
				.andExpect(jsonPath("$.content", is("content")))
				.andExpect(jsonPath("$.creationDate", is(creationDate.toString())));

	}

	@Test
	@WithMockUser(username = "user", password = "password", roles = "USER")
	public void shouldReturnAddPost() throws Exception {

		// given
		String json = 
				"{\n" + 
				"  \"content\": \"Test content\", \n" +
				"  \"title\": \"title\" \n" + 
				"	\n" + 
				"}";

		JSONObject jsonObj = new JSONObject(json.toString());

		Post post = PostFactory.create(jsonObj.get("title").toString(), jsonObj.get("content").toString(),
				LocalDateTime.now());
		
		// when
		when(postService.postAdd(post)).thenReturn("1");

		HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
		CsrfToken csrfToken = httpSessionCsrfTokenRepository.generateToken(new MockHttpServletRequest());

		// then
		mockMvc.perform(post("/posts/add/")
				.content(json.toString())
				.sessionAttr(TOKEN_ATTR_NAME, csrfToken)
				.param(csrfToken.getParameterName(), csrfToken.getToken()))
				.andExpect(status().isCreated());

	}
}