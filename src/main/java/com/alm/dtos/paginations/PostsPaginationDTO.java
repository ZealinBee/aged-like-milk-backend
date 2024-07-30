package com.alm.dtos.paginations;

import com.alm.dtos.posts.PostDTO;

import java.util.List;

public class PostsPaginationDTO extends PaginationDTO {
    private List<PostDTO> posts;

    public PostsPaginationDTO(int pageNumber, int pageSize, int totalPages, long totalElements, boolean isLast, List<PostDTO> posts) {
        super(pageNumber, pageSize, totalPages, totalElements, isLast);
        this.posts = posts;
    }

    public List<PostDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDTO> posts) {
        this.posts = posts;
    }
}
