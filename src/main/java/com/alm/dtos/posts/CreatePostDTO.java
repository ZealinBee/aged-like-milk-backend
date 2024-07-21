package com.alm.dtos.posts;

import java.util.UUID;

public class CreatePostDTO {
    private String title;
    private String content;
    private UUID categoryId;
    private UUID userId;

    public CreatePostDTO() {
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CreatePostDTO)) return false;
        final CreatePostDTO other = (CreatePostDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$title = this.getTitle();
        final Object other$title = other.getTitle();
        if (this$title == null ? other$title != null : !this$title.equals(other$title)) return false;
        final Object this$content = this.getContent();
        final Object other$content = other.getContent();
        if (this$content == null ? other$content != null : !this$content.equals(other$content)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CreatePostDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $title = this.getTitle();
        result = result * PRIME + ($title == null ? 43 : $title.hashCode());
        final Object $content = this.getContent();
        result = result * PRIME + ($content == null ? 43 : $content.hashCode());
        return result;
    }

    public String toString() {
        return "CreatePostDTO(title=" + this.getTitle() + ", content=" + this.getContent() + ")";
    }

    public UUID getCategoryId() {
        return this.categoryId;
    }

    public UUID getUserId() {
        return this.userId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
