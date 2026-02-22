package com.mahmoud.Blog_Platform.services;

import com.mahmoud.Blog_Platform.entities.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<Category> findAllCategories();
    Category createCategory(Category category);
    void deleteCategory(UUID categoryId);
    Category findCategoryById(UUID categoryId);
}
