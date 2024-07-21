package com.alm.services.abstractions;

import com.alm.dtos.posts.CreatePostDTO;
import com.alm.entities.Category;
import com.alm.entities.Post;

import java.util.UUID;

public interface IPostService {
    Post createPost(CreatePostDTO createPostDTO);
    Category validateCategory(UUID categoryId);
}
