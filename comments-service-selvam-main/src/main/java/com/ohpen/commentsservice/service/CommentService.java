package com.ohpen.commentsservice.service;

import com.ohpen.commentsservice.model.Comments;
import com.ohpen.commentsservice.model.dto.PostDto;
import com.ohpen.commentsservice.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ohpen.commentsservice.model.dto.CommentDto;
import com.ohpen.commentsservice.model.dto.NewCommentDto;
import com.ohpen.commentsservice.repository.PostRepository;

@Service
public class CommentService {

	private final CommentRepository commentRepository;

	private final PostRepository postRepository;

	public CommentService(CommentRepository commentRepository,PostRepository postRepository) {
		this.commentRepository = commentRepository;
		this.postRepository=postRepository;
	}

	/**
	 * Returns a list of all comments for a blog post with passed id.
	 *
	 * @param postId id of the post
	 * @return list of comments sorted by creation date descending - most recent first
	 *
	 * @throws IllegalArgumentException if there is no blog post for given postId
	 */
	public List<CommentDto> getCommentsForPost(Long postId) {
		// TODO implement

		postRepository.findById(postId)
				.map(post -> new PostDto(post.getTitle(), post.getContent(), post.getCreationDate()))
				.orElseThrow(()->new IllegalArgumentException() );



		List<CommentDto> comments = commentRepository.findByPostid(postId)
			.stream().map(Comments -> new CommentDto(Comments.getId(), Comments.getComment(), Comments.getAuthor(), Comments.getCreationDate()))
			 .sorted((c1,c2)->c2.getCreationDate().compareTo(c1.getCreationDate())).collect(Collectors.toList());

	return comments;

	}

	/**
	 * Creates a new comment
	 *
	 * @param postId id of the parent post
	 * @param newCommentDto data of new comment
	 * @return id of the created comment
	 *
	 * @throws IllegalArgumentException if there is no blog post for given postId
	 */
	public Long addComment(Long postId, NewCommentDto newCommentDto) {
		// TODO implement

		postRepository.findById(postId)
				.map(post -> new PostDto(post.getTitle(), post.getContent(), post.getCreationDate()))
				.orElseThrow(()->new IllegalArgumentException() );


		Comments c1=new Comments();
		//c1.setId(i);
		c1.setComment(newCommentDto.getContent());
		c1.setAuthor(newCommentDto.getAuthor());
		c1.setPostid(postId);
		c1.setCreationDate(LocalDateTime.now());
		return commentRepository.save(c1).getId();
	}

}
