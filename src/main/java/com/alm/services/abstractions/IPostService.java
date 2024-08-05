package com.alm.services.abstractions;

import com.alm.dtos.paginations.PostsPaginationDTO;
import com.alm.dtos.posts.CreatePostDTO;
import com.alm.dtos.posts.GetPostDTO;
import com.alm.dtos.posts.UpdatePostDTO;
import com.alm.entities.Category;

import java.util.UUID;

public interface IPostService {
    GetPostDTO createPost(CreatePostDTO createPostDTO);
    Category validateCategory(UUID categoryId);
    PostsPaginationDTO findAllPosts(int pageNumber, int pageSize);
    GetPostDTO findPostById(UUID postId);
    void deletePostById(UUID postId);
    GetPostDTO updatePostById(UUID postId, UpdatePostDTO updatePostDTO);
}
