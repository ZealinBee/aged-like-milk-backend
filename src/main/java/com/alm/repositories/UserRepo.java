package com.alm.repositories;

import com.alm.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {
    User findOneById(UUID userId);
}
