package com.alm.services.abstractions;

import com.alm.dtos.posts.PostDTO;
import com.alm.dtos.posts.UpdatePostDTO;
import com.alm.entities.Category;
import com.alm.entities.Post;

import java.util.List;
import java.util.UUID;

public interface IPostService {
    PostDTO createPost(PostDTO createPostDTO);
    Category validateCategory(UUID categoryId);
    List<PostDTO> findAllPosts();
    PostDTO findPostById(UUID postId);
    boolean deletePostById(UUID postId);
    PostDTO updatePostById(UUID postId, UpdatePostDTO updatePostDTO);
}
