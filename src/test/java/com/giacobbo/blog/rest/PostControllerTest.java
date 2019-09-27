package com.giacobbo.blog.rest;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.Test;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;

import com.giacobbo.blog.dto.PostDto;

@ContextConfiguration(classes = PostController.class)
public class PostControllerTest extends AbstractControllerTest {

	@Test
	@WithMockUser(username = "user", password = "password", roles = "USER")
	public void shouldReturnFoundPost() throws Exception {
		// given
		LocalDateTime creationDate = LocalDateTime.of(2018, 5, 20, 20, 51, 16);
		PostDto post = new PostDto(1L, "Title", "content", creationDate);

		// when
		when(postService.getPost("1")).thenReturn(post);

		// then
		mockMvc.perform(get("/posts/1").accept(APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.title", is("Title")))
				.andExpect(jsonPath("$.content", is("content")))
				.andExpect(jsonPath("$.creationDate", is(creationDate.toString())));

	}
}