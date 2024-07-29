package com.alm.services.implementations;

import com.alm.dtos.categories.CreateCategoryDTO;
import com.alm.entities.Category;
import com.alm.exceptions.CustomRunTimeException;
import com.alm.mappers.CategoryMapper;
import com.alm.repositories.CategoryRepo;
import com.alm.services.abstractions.ICategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService implements ICategoryService {
    private final CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public List<Category> findAllCategory() {
        return categoryRepo.findAll();
    }

    @Override
    public Category findCategoryById(UUID categoryId) {
        return categoryRepo.findById(categoryId).orElseThrow(
                () -> new CustomRunTimeException("404", HttpStatus.NOT_FOUND, "The category ID does not exist"));
    }

    public Category createCategory(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public Category updateCategoryById(UUID categoryId, Category category) {
        var foundCategory = categoryRepo.findById(categoryId).orElseThrow(
                () -> new CustomRunTimeException("404", HttpStatus.NOT_FOUND, "The category ID does not exist"));
        foundCategory.setName(category.getName());
        foundCategory.setDescription(category.getDescription());
        return categoryRepo.save(foundCategory);
    }

    @Override
    public void deleteCategoryById(UUID categoryId) {
        categoryRepo.deleteById(categoryId);
    }
}
