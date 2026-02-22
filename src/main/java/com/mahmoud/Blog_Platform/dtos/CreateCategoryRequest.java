package com.mahmoud.Blog_Platform.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateCategoryRequest(
        @NotNull(message = "Category name is required")
        @Size(min = 2, max = 50, message = "Category name must be between {min} and {max} characters")
        @Pattern(regexp = "^[\\w\\s-]+$", message = "Category can only contain letters, numbers, spaces, and hyphens")
        String name
) {
}
