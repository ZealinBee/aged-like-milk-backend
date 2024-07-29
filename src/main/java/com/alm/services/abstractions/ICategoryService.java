package com.alm.services.abstractions;

import com.alm.dtos.categories.CreateCategoryDTO;
import com.alm.entities.Category;

import java.util.List;
import java.util.UUID;

public interface ICategoryService {
    List<Category> findAllCategory();
    Category findCategoryById(UUID categoryId);
    Category createCategory(Category category);
    Category updateCategoryById(UUID categoryId, Category category);
    void deleteCategoryById(UUID categoryId);


}
