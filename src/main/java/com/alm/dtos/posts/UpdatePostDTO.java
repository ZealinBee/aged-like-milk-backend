package com.alm.dtos.posts;

import java.util.UUID;

public class UpdatePostDTO {
    private String title;
    private String content;
    private UUID categoryId;

    public UpdatePostDTO() {
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public UUID getCategoryId() {
        return this.categoryId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UpdatePostDTO)) return false;
        final UpdatePostDTO other = (UpdatePostDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$title = this.getTitle();
        final Object other$title = other.getTitle();
        if (this$title == null ? other$title != null : !this$title.equals(other$title)) return false;
        final Object this$content = this.getContent();
        final Object other$content = other.getContent();
        if (this$content == null ? other$content != null : !this$content.equals(other$content)) return false;
        final Object this$categoryId = this.getCategoryId();
        final Object other$categoryId = other.getCategoryId();
        if (this$categoryId == null ? other$categoryId != null : !this$categoryId.equals(other$categoryId))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UpdatePostDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $title = this.getTitle();
        result = result * PRIME + ($title == null ? 43 : $title.hashCode());
        final Object $content = this.getContent();
        result = result * PRIME + ($content == null ? 43 : $content.hashCode());
        final Object $categoryId = this.getCategoryId();
        result = result * PRIME + ($categoryId == null ? 43 : $categoryId.hashCode());
        return result;
    }

    public String toString() {
        return "UpdatePostDTO(title=" + this.getTitle() + ", content=" + this.getContent() + ", categoryId=" + this.getCategoryId() + ")";
    }
}
