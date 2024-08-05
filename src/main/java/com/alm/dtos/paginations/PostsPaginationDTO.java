package com.alm.dtos.paginations;

import com.alm.dtos.posts.GetPostDTO;

import java.util.List;

public class PostsPaginationDTO extends PaginationDTO {
    private List<GetPostDTO> posts;

    public PostsPaginationDTO(int pageNumber, int pageSize, int totalPages, long totalElements, boolean isLast, List<GetPostDTO> posts) {
        super(pageNumber, pageSize, totalPages, totalElements, isLast);
        this.posts = posts;
    }

    public List<GetPostDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<GetPostDTO> posts) {
        this.posts = posts;
    }
}
