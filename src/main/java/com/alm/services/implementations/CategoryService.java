package com.alm.services.implementations;

import com.alm.dtos.categories.CreateCategoryDTO;
import com.alm.entities.Category;
import com.alm.mappers.CategoryMapper;
import com.alm.repositories.CategoryRepo;
import com.alm.services.abstractions.ICategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    private final CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public List<Category> findAllCategory() {
        return categoryRepo.findAll();
    }

    public Category createCategory(Category category) {
        return categoryRepo.save(category);
    }
}
