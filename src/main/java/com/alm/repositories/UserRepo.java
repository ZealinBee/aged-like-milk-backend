package com.alm.repositories;

import com.alm.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepo extends JpaRepository<User, UUID> {
    User findOneById(UUID userId);
    Optional<User> findOneByEmail(String email);
}
