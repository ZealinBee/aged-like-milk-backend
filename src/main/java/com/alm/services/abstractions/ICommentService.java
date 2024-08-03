package com.alm.services.abstractions;

import com.alm.dtos.comments.CreateCommentDTO;
import com.alm.dtos.comments.GetCommentDTO;

import java.util.List;
import java.util.UUID;

public interface ICommentService {
    GetCommentDTO createComment(CreateCommentDTO createCommentDTO, UUID userId);
    List<GetCommentDTO> findAllCommentsByPostId(UUID postId);
    void deleteCommentById(UUID commentId);
    GetCommentDTO likeComment(UUID commentId, UUID userId);
    GetCommentDTO dislikeComment(UUID commentId, UUID userId);
}
