package com.alm.services.implementations;

import com.alm.dtos.paginations.PostsPaginationDTO;
import com.alm.dtos.posts.CreatePostDTO;
import com.alm.dtos.posts.GetPostDTO;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public GetPostDTO createPost(CreatePostDTO createPostDTO) {
        Category postCategory = validateCategory(createPostDTO.getCategoryId());
        User postUser = validateUser(createPostDTO.getUserId());
        Post newPost = new Post();
        newPost.setTitle(createPostDTO.getTitle());
        newPost.setContent(createPostDTO.getContent());
        newPost.setUser(postUser);
        newPost.setCategory(postCategory);
        var savedPost = postRepo.save(newPost);
        return postMapper.postToGetPostDTO(savedPost);
    }

    public Category validateCategory(UUID categoryId) {
        return categoryRepo.findOneById(categoryId)
                .orElseThrow(() -> new CustomRunTimeException("404", HttpStatus.NOT_FOUND, "The category ID doesn't exist"));
    }

    @Override
    public PostsPaginationDTO findAllPosts(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Post> posts = postRepo.findAll(pageable);
        List<Post> postsList = posts.getContent();
        List<GetPostDTO> mappedPosts =  postsList.stream()
                .map(postMapper::postToGetPostDTO)
                .toList();

        return new PostsPaginationDTO(
                posts.getNumber(),
                posts.getSize(),
                posts.getTotalPages(),
                posts.getTotalElements(),
                posts.isLast(),
                mappedPosts
        );
    }

    @Override
    public GetPostDTO findPostById(UUID postId) {
        var post = postRepo.findById(postId)
                .orElseThrow(() -> new CustomRunTimeException("404", HttpStatus.NOT_FOUND,"The post ID does not exist"));
        return postMapper.postToGetPostDTO(post);
    }

    @Override
    public void deletePostById(UUID postId) {
        postRepo.deleteById(postId);
    }

    @Override
    public GetPostDTO updatePostById(UUID postId, UpdatePostDTO updatePostDTO) {
        var foundPost = postRepo.findById(postId)
                .orElseThrow(() -> new CustomRunTimeException("404", HttpStatus.NOT_FOUND,"The post ID does not exist"));
        var newCategory = validateCategory(updatePostDTO.getCategoryId());
        foundPost.setTitle(updatePostDTO.getTitle());
        foundPost.setContent(updatePostDTO.getContent());
        foundPost.setCategory(newCategory);
        postRepo.save(foundPost);
        return postMapper.postToGetPostDTO(foundPost);
    }

    public User validateUser(UUID userId) {
        return userRepo.findOneById(userId);
    }
}
