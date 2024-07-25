package com.alm.controllers;

import com.alm.dtos.posts.CreatePostDTO;
import com.alm.entities.Post;
import com.alm.services.abstractions.IPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private final IPostService postService;

    public PostController(IPostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody CreatePostDTO createPostDTO) {
        var post = postService.createPost(createPostDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

}
