package com.mahmoud.Blog_Platform.services.impl;

import com.mahmoud.Blog_Platform.entities.Category;
import com.mahmoud.Blog_Platform.repositories.CategoryRepository;
import com.mahmoud.Blog_Platform.services.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl  implements CategoryService {
    private final CategoryRepository categoryRepository;


    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAllWithPostCount();
    }

    @Override
    public Category createCategory(Category category) {
        String categoryName = category.getName();
        if (categoryRepository.existsByNameIgnoreCase(categoryName)) {
            throw new IllegalArgumentException("Category with name "+ categoryName +" already exists");
        }
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(UUID categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            if (!category.get().getPosts().isEmpty()) {
                throw new IllegalStateException("Category has posts associated with it");
            }
            categoryRepository.deleteById(categoryId);
        }
    }

    @Override
    public Category findCategoryById(UUID categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category with id "+ categoryId + " not found"));
    }
}
