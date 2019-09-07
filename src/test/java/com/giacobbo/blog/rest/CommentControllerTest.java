package com.giacobbo.blog.rest;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.giacobbo.blog.dto.CommentDto;

@WebMvcTest(CommentController.class)
public class CommentControllerTest extends AbstractControllerTest {

	private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	private static final String TOKEN_ATTR_NAME = "org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN";

	@Test
	@WithMockUser(username = "user", password = "password", roles = "USER")
	public void shouldReturnFoundComments() throws Exception {

		// given
		List<CommentDto> comments = new ArrayList<>();
		LocalDateTime creationDate = LocalDateTime.of(2018, 5, 20, 20, 51, 16);
		comments.add(new CommentDto(1L, "comment content", "John Smith", creationDate));

		// when
		when(commentService.getCommentsForPost("1")).thenReturn(comments);

		// then
		mockMvc.perform(get("/comments/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].comment", is("comment content")))
				.andExpect(jsonPath("$[0].author", is("John Smith")))
				.andExpect(jsonPath("$[0].creationDate", is(creationDate.toString())));

	}

	@Test
	@WithMockUser(username = "user", password = "password", roles = "USER")
	public void shouldAddComment() throws Exception {

		// given

		String commentBody = "{\"content\":\"Test content\", \"author\":\"John Doe\"}";
		CommentDto newComment = createComment("Test content", "John Doe");

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		commentBody = ow.writeValueAsString(newComment);

		HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
		CsrfToken csrfToken = httpSessionCsrfTokenRepository.generateToken(new MockHttpServletRequest());

		// when
		when(commentService.addComment(newComment)).thenReturn("1");

		// then .with(
		mockMvc.perform(post("/comments/1")
				.sessionAttr(TOKEN_ATTR_NAME, csrfToken)
                .param(csrfToken.getParameterName(), csrfToken.getToken())
				.contentType(APPLICATION_JSON_UTF8).content(commentBody)
				.accept(APPLICATION_JSON_UTF8)).andExpect(status()
						.isCreated());
	}

	private CommentDto createComment(String content, String author) {
		CommentDto newComment = new CommentDto();
		newComment.setComment(content);
		newComment.setAuthor(author);
		return newComment;
	}

}