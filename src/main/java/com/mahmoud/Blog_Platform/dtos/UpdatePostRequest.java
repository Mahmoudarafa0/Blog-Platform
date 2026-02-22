package com.mahmoud.Blog_Platform.dtos;

import com.mahmoud.Blog_Platform.entities.enums.PostStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;
import java.util.UUID;

public record UpdatePostRequest(
        @NotNull(message = "Title is required")
        @Size(min = 3, max = 200, message = "Title name must be between {min} and {max} characters")
        String title,

        @NotNull(message = "Content is required")
        @Size(min = 10, max = 50000, message = "Content name must be between {min} and {max} characters")
        String content,

        @NotNull(message = "Category id is required")
        UUID categoryId,

        @Size(max = 10, message = "maximum {max} tags allowed")
        Set<UUID> tagIds,

        @NotNull(message = "Status is required")
        PostStatus status
) {
}
