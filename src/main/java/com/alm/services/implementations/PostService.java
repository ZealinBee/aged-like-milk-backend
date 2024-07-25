package com.alm.services.implementations;

import com.alm.dtos.posts.CreatePostDTO;
import com.alm.entities.Category;
import com.alm.entities.Post;
import com.alm.entities.User;
import com.alm.exceptions.CustomRunTimeException;
import com.alm.repositories.CategoryRepo;
import com.alm.repositories.PostRepo;
import com.alm.repositories.UserRepo;
import com.alm.services.abstractions.IPostService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PostService implements IPostService {
    private final PostRepo postRepo;
    private final CategoryRepo categoryRepo;
    private final UserRepo userRepo;
    public PostService(PostRepo postRepo, CategoryRepo categoryRepo, UserRepo userRepo) {
        this.postRepo = postRepo;
        this.categoryRepo = categoryRepo;
        this.userRepo = userRepo;
    }

    public Post createPost(CreatePostDTO createPostDTO) {
        Category postCategory = validateCategory(createPostDTO.getCategoryId());
        User postUser = validateUser(createPostDTO.getUserId());
        Post newPost = new Post();
        newPost.setTitle(createPostDTO.getTitle());
        newPost.setContent(createPostDTO.getContent());
        newPost.setUser(postUser);
        newPost.setCategory(postCategory);
        return postRepo.save(newPost);
    }

    public Category validateCategory(UUID categoryId) {
        return categoryRepo.findOneById(categoryId)
                .orElseThrow(() -> new CustomRunTimeException("404", HttpStatus.NOT_FOUND, "The category ID doesn't exist"));
    }

    public User validateUser(UUID userId) {
        return userRepo.findOneById(userId);
    }
}
