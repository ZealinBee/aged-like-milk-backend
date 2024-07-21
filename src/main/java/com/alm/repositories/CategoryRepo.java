package com.alm.repositories;

import com.alm.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepo extends JpaRepository<Category, UUID> {
    Category findOneById(UUID categoryId);
}
