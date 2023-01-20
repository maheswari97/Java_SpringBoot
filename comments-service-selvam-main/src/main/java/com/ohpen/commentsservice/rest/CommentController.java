package com.ohpen.commentsservice.rest;

import com.ohpen.commentsservice.model.dto.CommentDto;
import com.ohpen.commentsservice.model.dto.NewCommentDto;
import com.ohpen.commentsservice.model.dto.PostDto;
import com.ohpen.commentsservice.service.CommentService;
import com.ohpen.commentsservice.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping(value = "/{id}/comments")
    public List<CommentDto> getPost(@PathVariable Long id) {
        return commentService.getCommentsForPost(id);
    }

    @PostMapping (value = "/{id}/comments")
    public long getPost(@PathVariable Long id,@RequestBody NewCommentDto newCommentDto) {
        return commentService.addComment(id,newCommentDto);
    }



}
