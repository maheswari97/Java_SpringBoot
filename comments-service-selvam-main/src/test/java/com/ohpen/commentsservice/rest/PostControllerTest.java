package com.ohpen.commentsservice.rest;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import com.ohpen.commentsservice.model.dto.PostDto;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PostControllerTest extends AbstractControllerTest {

	@Test
	void shouldReturnFoundPost() throws Exception {
		LocalDateTime creationDate = LocalDateTime.of(2018, 5, 20, 20, 51, 16);
		PostDto post = new PostDto("Title", "content", creationDate);

		when(postService.getPost(1L)).thenReturn(post);

		mockMvc.perform(get("/posts/1").accept(APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON))
				.andExpect(jsonPath("$.title", is("Title")))
				.andExpect(jsonPath("$.content", is("content")))
				.andExpect(jsonPath("$.creationDate", is(creationDate.toString())));

	}
}
