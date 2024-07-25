package com.alm.repositories;

import com.alm.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface PostRepo extends JpaRepository<Post, UUID> {
}
