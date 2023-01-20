package com.ohpen.commentsservice.service;


import org.springframework.stereotype.Service;

import com.ohpen.commentsservice.model.dto.PostDto;
import com.ohpen.commentsservice.repository.PostRepository;

@Service
public class PostService {

	private final PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public PostDto getPost(Long id) {
		return postRepository.findById(id)
				.map(post -> new PostDto(post.getTitle(), post.getContent(), post.getCreationDate()))
				.orElse(null);
	}
}
