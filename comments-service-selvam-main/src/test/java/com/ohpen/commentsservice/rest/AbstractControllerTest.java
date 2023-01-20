package com.ohpen.commentsservice.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.ohpen.commentsservice.service.CommentService;
import com.ohpen.commentsservice.service.PostService;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public abstract class AbstractControllerTest {

	@Autowired
	protected MockMvc mockMvc;

	@MockBean
	protected PostService postService;

	@MockBean
	protected CommentService commentService;

	@BeforeEach
	public void setUp() {
		Mockito.reset(postService, commentService);
	}

}
