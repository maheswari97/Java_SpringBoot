package com.ohpen.commentsservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import com.ohpen.commentsservice.model.Post;
import com.ohpen.commentsservice.model.dto.CommentDto;
import com.ohpen.commentsservice.model.dto.NewCommentDto;
import com.ohpen.commentsservice.repository.PostRepository;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CommentServiceTest {

	@Autowired
	PostRepository postRepository;

	@Autowired
	CommentService commentService;

	@Test
	void shouldAddComment() {
		Post post = createTestPost();

		NewCommentDto comment = new NewCommentDto();
		comment.setAuthor("Author");
		comment.setContent("Content");
		Long commentId = commentService.addComment(post.getId(), comment);

		assertThat(commentId).withFailMessage("Comment id shouldn't be null").isNotNull();
	}

	private Post createTestPost() {
		Post post = new Post();
		post.setTitle("Test title");
		post.setContent("Test content");
		LocalDateTime creationDate = LocalDateTime.of(2018, 5, 20, 20, 51, 16);
		post.setCreationDate(creationDate);
		postRepository.save(post);
		return post;
	}

	@Test
	void shouldReturnAddedComment() {
		Post post = createTestPost();

		NewCommentDto comment = new NewCommentDto();
		comment.setAuthor("Author");
		comment.setContent("Content");

		commentService.addComment(post.getId(), comment);

		List<CommentDto> comments = commentService.getCommentsForPost(post.getId());

		assertThat(comments).withFailMessage("There should be one comment").hasSize(1);
		assertThat(comments.get(0).getAuthor()).isEqualTo("Author");
		assertThat(comments.get(0).getComment()).isEqualTo("Content");
	}
}
