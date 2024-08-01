package com.alm.dtos.comments;

import java.util.Date;
import java.util.UUID;

public class GetCommentDTO {
    private UUID id;
    private UUID postId;
    private String content;
    private UUID userId;
    private UUID parentCommentId;
    private Date createdDate;

    public GetCommentDTO() {
    }

    public UUID getId() {
        return this.id;
    }

    public UUID getPostId() {
        return this.postId;
    }

    public String getContent() {
        return this.content;
    }

    public UUID getUserId() {
        return this.userId;
    }

    public UUID getParentCommentId() {
        return this.parentCommentId;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setPostId(UUID postId) {
        this.postId = postId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setParentCommentId(UUID parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof GetCommentDTO)) return false;
        final GetCommentDTO other = (GetCommentDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$postId = this.getPostId();
        final Object other$postId = other.getPostId();
        if (this$postId == null ? other$postId != null : !this$postId.equals(other$postId)) return false;
        final Object this$content = this.getContent();
        final Object other$content = other.getContent();
        if (this$content == null ? other$content != null : !this$content.equals(other$content)) return false;
        final Object this$userId = this.getUserId();
        final Object other$userId = other.getUserId();
        if (this$userId == null ? other$userId != null : !this$userId.equals(other$userId)) return false;
        final Object this$parentCommentId = this.getParentCommentId();
        final Object other$parentCommentId = other.getParentCommentId();
        if (this$parentCommentId == null ? other$parentCommentId != null : !this$parentCommentId.equals(other$parentCommentId))
            return false;
        final Object this$createdDate = this.getCreatedDate();
        final Object other$createdDate = other.getCreatedDate();
        if (this$createdDate == null ? other$createdDate != null : !this$createdDate.equals(other$createdDate))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof GetCommentDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $postId = this.getPostId();
        result = result * PRIME + ($postId == null ? 43 : $postId.hashCode());
        final Object $content = this.getContent();
        result = result * PRIME + ($content == null ? 43 : $content.hashCode());
        final Object $userId = this.getUserId();
        result = result * PRIME + ($userId == null ? 43 : $userId.hashCode());
        final Object $parentCommentId = this.getParentCommentId();
        result = result * PRIME + ($parentCommentId == null ? 43 : $parentCommentId.hashCode());
        final Object $createdDate = this.getCreatedDate();
        result = result * PRIME + ($createdDate == null ? 43 : $createdDate.hashCode());
        return result;
    }

    public String toString() {
        return "GetCommentDTO(id=" + this.getId() + ", postId=" + this.getPostId() + ", content=" + this.getContent() + ", userId=" + this.getUserId() + ", parentCommentId=" + this.getParentCommentId() + ", createdDate=" + this.getCreatedDate() + ")";
    }
}
