package com.alm.services.abstractions;

import com.alm.dtos.paginations.PostsPaginationDTO;
import com.alm.dtos.posts.PostDTO;
import com.alm.dtos.posts.UpdatePostDTO;
import com.alm.entities.Category;

import java.util.List;
import java.util.UUID;

public interface IPostService {
    PostDTO createPost(PostDTO createPostDTO);
    Category validateCategory(UUID categoryId);
    PostsPaginationDTO findAllPosts(int pageNumber, int pageSize);
    PostDTO findPostById(UUID postId);
    void deletePostById(UUID postId);
    PostDTO updatePostById(UUID postId, UpdatePostDTO updatePostDTO);
}
