package com.alm.mappers;

import com.alm.dtos.posts.GetPostDTO;
import com.alm.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "comments", target = "comments")
    GetPostDTO postToGetPostDTO(Post post);
}
