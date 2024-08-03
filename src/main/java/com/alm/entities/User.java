package com.alm.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {
    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.ORDINAL)
    private Role role;

    @ElementCollection
    @CollectionTable(name = "user_liked_comments", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "liked_comment_id")
    private List<UUID> likedComments;

    @ElementCollection
    @CollectionTable(name = "user_disliked_comments", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "disliked_comment_id")
    private List<UUID> dislikedComments;

    public User(UUID id, String email, String password, Role role, List<UUID> likedComments, List<UUID> dislikedComments) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.likedComments = likedComments;
        this.dislikedComments = dislikedComments;
    }

    public User() {
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        // email is the main thing, getUsername is from UserDetails interface
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UUID getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public Role getRole() {
        return this.role;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<UUID> getLikedComments() {
        return this.likedComments;
    }

    public List<UUID> getDislikedComments() {
        return this.dislikedComments;
    }

    public void setLikedComments(List<UUID> likedComments) {
        this.likedComments = likedComments;
    }

    public void setDislikedComments(List<UUID> dislikedComments) {
        this.dislikedComments = dislikedComments;
    }

}

