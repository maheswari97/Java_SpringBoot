package com.ohpen.commentsservice.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ohpen.commentsservice.model.dto.PostDto;
import com.ohpen.commentsservice.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {

	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping(value = "/{id}")
	public PostDto getPost(@PathVariable Long id) {
		return postService.getPost(id);
	}

}
