package com.alm.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(columnDefinition = "int default 0")
    private int likes;

    @Column(columnDefinition = "int default 0")
    private int dislikes;

    @Column(columnDefinition = "int default 0")
    private int nestingLevel;

    @Column
    private UUID parentCommentId;

    public Comment(UUID id, String content, User user, Post post, int likes, int dislikes, int nestingLevel, UUID parentCommentId) {
        this.id = id;
        this.content = content;
        this.user = user;
        this.post = post;
        this.likes = likes;
        this.dislikes = dislikes;
        this.nestingLevel = nestingLevel;
        this.parentCommentId = parentCommentId;
    }

    public Comment() {
    }

    public UUID getId() {
        return this.id;
    }

    public String getContent() {
        return this.content;
    }

    public User getUser() {
        return this.user;
    }

    public Post getPost() {
        return this.post;
    }

    public int getLikes() {
        return this.likes;
    }

    public int getDislikes() {
        return this.dislikes;
    }

    public int getNestingLevel() {
        return this.nestingLevel;
    }

    public UUID getParentCommentId() {
        return this.parentCommentId;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public void setNestingLevel(int nestingLevel) {
        this.nestingLevel = nestingLevel;
    }

    public void setParentCommentId(UUID parentCommentId) {
        this.parentCommentId = parentCommentId;
    }
}
