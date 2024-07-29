package com.alm.services.implementations;

import com.alm.dtos.posts.PostDTO;
import com.alm.dtos.posts.UpdatePostDTO;
import com.alm.entities.Category;
import com.alm.entities.Post;
import com.alm.entities.User;
import com.alm.exceptions.CustomRunTimeException;
import com.alm.mappers.PostMapper;
import com.alm.repositories.CategoryRepo;
import com.alm.repositories.PostRepo;
import com.alm.repositories.UserRepo;
import com.alm.services.abstractions.IPostService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostService implements IPostService {
    private final PostRepo postRepo;
    private final CategoryRepo categoryRepo;
    private final UserRepo userRepo;
    private final PostMapper postMapper;
    public PostService(PostRepo postRepo, CategoryRepo categoryRepo, UserRepo userRepo, PostMapper postMapper) {
        this.postRepo = postRepo;
        this.categoryRepo = categoryRepo;
        this.userRepo = userRepo;
        this.postMapper = postMapper;
    }

    public PostDTO createPost(PostDTO postDTO) {
        Category postCategory = validateCategory(postDTO.getCategoryId());
        User postUser = validateUser(postDTO.getUserId());
        Post newPost = new Post();
        newPost.setTitle(postDTO.getTitle());
        newPost.setContent(postDTO.getContent());
        newPost.setUser(postUser);
        newPost.setCategory(postCategory);
        var savedPost = postRepo.save(newPost);
        return postMapper.postToPostDTO(savedPost);
    }

    public Category validateCategory(UUID categoryId) {
        return categoryRepo.findOneById(categoryId)
                .orElseThrow(() -> new CustomRunTimeException("404", HttpStatus.NOT_FOUND, "The category ID doesn't exist"));
    }

    @Override
    public List<PostDTO> findAllPosts() {
        List<Post> posts = postRepo.findAll();
        return posts.stream()
                .map(postMapper::postToPostDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO findPostById(UUID postId) {
        var post = postRepo.findById(postId)
                .orElseThrow(() -> new CustomRunTimeException("404", HttpStatus.NOT_FOUND,"The post ID does not exist"));
        return postMapper.postToPostDTO(post);
    }

    @Override
    public boolean deletePostById(UUID postId) {
        postRepo.deleteById(postId);
        return true;
    }

    @Override
    public PostDTO updatePostById(UUID postId, UpdatePostDTO updatePostDTO) {
        var foundPost = postRepo.findById(postId)
                .orElseThrow(() -> new CustomRunTimeException("404", HttpStatus.NOT_FOUND,"The post ID does not exist"));
        var newCategory = validateCategory(updatePostDTO.getCategoryId());
        foundPost.setTitle(updatePostDTO.getTitle());
        foundPost.setContent(updatePostDTO.getContent());
        foundPost.setCategory(newCategory);
        postRepo.save(foundPost);
        return postMapper.postToPostDTO(foundPost);
    }

    public User validateUser(UUID userId) {
        return userRepo.findOneById(userId);
    }
}
