package com.alm.controllers;

import com.alm.dtos.paginations.PostsPaginationDTO;
import com.alm.dtos.posts.PostDTO;
import com.alm.dtos.posts.UpdatePostDTO;
import com.alm.entities.Post;
import com.alm.services.abstractions.IPostService;
import org.apache.coyote.Response;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private final IPostService postService;

    public PostController(IPostService postService) {
        this.postService = postService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN', 'ROLE_WRITER')")
    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {
        var post = postService.createPost(postDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @GetMapping
    public ResponseEntity<PostsPaginationDTO> findAllPosts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(postService.findAllPosts(page, size));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> findPostById(@PathVariable(value = "postId") UUID postId) {
        return ResponseEntity.ok(postService.findPostById(postId));
    }
    @PreAuthorize("hasRole('ROLE_ADMIN', 'ROLE_WRITER')")
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePostById(@PathVariable(value = "postId") UUID postId){
        postService.deletePostById(postId);
        return ResponseEntity.noContent().build();
    }
    @PreAuthorize("hasRole('ROLE_ADMIN', 'ROLE_WRITER')")
    @PutMapping("/{postId}")
    public ResponseEntity<PostDTO> updatePostById(@PathVariable(value = "postId") UUID postId, @RequestBody UpdatePostDTO postDTO) {
        return ResponseEntity.ok(postService.updatePostById(postId, postDTO));
    }

}
