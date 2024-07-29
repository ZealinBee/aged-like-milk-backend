package com.alm.controllers;

import com.alm.dtos.categories.CreateCategoryDTO;
import com.alm.entities.Category;
import com.alm.services.abstractions.ICategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final ICategoryService categoryService;
    private final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> findAllCategories() {
        return categoryService.findAllCategory();
    }

    @GetMapping("/{categoryId}")
    public Category findCategoryById(@PathVariable UUID categoryId) {
        return categoryService.findCategoryById(categoryId);
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.createCategory(category));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<?> updateCategoryById(@PathVariable UUID categoryId, @RequestBody Category category) {
        return ResponseEntity.ok(categoryService.updateCategoryById(categoryId, category));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable UUID categoryId) {
        categoryService.deleteCategoryById(categoryId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
