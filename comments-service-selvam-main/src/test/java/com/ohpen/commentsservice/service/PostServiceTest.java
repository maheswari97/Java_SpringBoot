package com.ohpen.commentsservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import com.ohpen.commentsservice.model.Post;
import com.ohpen.commentsservice.model.dto.PostDto;
import com.ohpen.commentsservice.repository.PostRepository;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PostServiceTest {

	@Autowired
	PostRepository postRepository;

	@Autowired
	PostService postService;

	@Test
	void shouldReturnCreatedPost() {
		Post post = new Post();
		post.setTitle("Test title");
		post.setContent("Test content");
		LocalDateTime creationDate = LocalDateTime.of(2018, 5, 20, 20, 51, 16);
		post.setCreationDate(creationDate);
		postRepository.save(post);

		PostDto postDto = postService.getPost(post.getId());

		assertThat(postDto).isNotNull();
		assertThat(postDto.getContent()).isEqualTo("Test content");
		assertThat(postDto.getTitle()).isEqualTo("Test title");
		assertThat(postDto.getCreationDate()).isEqualTo(creationDate);
	}

	@Test
	void shouldReturnNullForNotExistingPost() {
		PostDto postDto = postService.getPost(123L);

		assertThat(postDto).isNull();
	}
}
