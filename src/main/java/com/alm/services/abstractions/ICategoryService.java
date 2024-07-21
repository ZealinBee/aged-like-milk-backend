package com.alm.services.abstractions;

import com.alm.dtos.categories.CreateCategoryDTO;
import com.alm.entities.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAllCategory();
    Category createCategory(Category category);

}
