package com.ohpen.commentsservice.rest;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ohpen.commentsservice.model.dto.CommentDto;
import com.ohpen.commentsservice.model.dto.NewCommentDto;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CommentControllerTest extends AbstractControllerTest {

	@Test
	void shouldReturnFoundComments() throws Exception {
		List<CommentDto> comments = new ArrayList<>();
		LocalDateTime creationDate = LocalDateTime.of(2018, 5, 20, 20, 51, 16);
		comments.add(new CommentDto(2L, "comment content", "John Smith", creationDate));

		when(commentService.getCommentsForPost(1L)).thenReturn(comments);

		mockMvc.perform(get("/posts/1/comments").accept(MediaType.APPLICATION_JSON))
			   .andExpect(status().isOk())
			   .andExpect(jsonPath("$", hasSize(1)))
			   .andExpect(jsonPath("$[0].id", is(2)))
			   .andExpect(jsonPath("$[0].comment", is("comment content")))
			   .andExpect(jsonPath("$[0].author", is("John Smith")))
			   .andExpect(jsonPath("$[0].creationDate", is(creationDate.toString())));

	}

	@Test
	void shouldAddComment() throws Exception {
		String commentBody = "{\"content\":\"Test content\", \"author\":\"John Doe\"}";
		NewCommentDto newComment = createComment();

		when(commentService.addComment(1L, newComment)).thenReturn(1L);

		mockMvc.perform(post("/posts/1/comments")
								.content(commentBody)
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON))
			   .andExpect(status().isCreated());
	}

	private NewCommentDto createComment() {
		NewCommentDto newComment = new NewCommentDto();
		newComment.setContent("Test content");
		newComment.setAuthor("John Doe");
		return newComment;
	}

}
