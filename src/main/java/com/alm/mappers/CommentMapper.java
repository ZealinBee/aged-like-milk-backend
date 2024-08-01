package com.alm.mappers;

import com.alm.dtos.comments.GetCommentDTO;
import com.alm.entities.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "post.id", target = "postId")
    GetCommentDTO commentToGetCommentDTO(Comment comment);
}
