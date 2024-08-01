package com.alm.controllers;

import com.alm.dtos.comments.CreateCommentDTO;
import com.alm.dtos.comments.GetCommentDTO;
import com.alm.services.abstractions.ICommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {
    private final ICommentService commentService;

    public CommentController(ICommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<GetCommentDTO> createComment(@RequestBody CreateCommentDTO createCommentDTO) {
        return ResponseEntity.ok(commentService.createComment(createCommentDTO));
    }
}
