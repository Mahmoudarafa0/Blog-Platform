package com.mahmoud.Blog_Platform.controllers;

import com.mahmoud.Blog_Platform.Mappers.CategoryMapper;
import com.mahmoud.Blog_Platform.dtos.CategoryDto;
import com.mahmoud.Blog_Platform.dtos.CreateCategoryRequest;
import com.mahmoud.Blog_Platform.entities.Category;
import com.mahmoud.Blog_Platform.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Category", description = "Operations related to categories")
@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @Operation(summary = "Get all categories")
    @GetMapping
    private ResponseEntity<List<CategoryDto>> findAllCategories() {
        List<CategoryDto>  categories = categoryService.findAllCategories()
                .stream().map(categoryMapper::toDto).toList();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @Operation(summary = "Create a new category")
    @PostMapping
    private ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CreateCategoryRequest createCategoryRequest) {
        Category category = categoryMapper.toEntity(createCategoryRequest);
        Category createdCategory = categoryService.createCategory(category);
        return new ResponseEntity<>(categoryMapper.toDto(createdCategory), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a specific category")
    @DeleteMapping("/{categoryId}")
    private ResponseEntity<Void> deleteCategory(@PathVariable UUID categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
