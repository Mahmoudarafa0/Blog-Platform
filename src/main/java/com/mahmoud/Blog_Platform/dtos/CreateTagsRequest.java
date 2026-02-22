package com.mahmoud.Blog_Platform.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record CreateTagsRequest(
        @NotEmpty(message = "At least one tag name is required")
        @Size(max = 10, message = "Maximum {max} tags allowed")
        Set<
                @Size(min = 2, max = 50, message = "Tag name must be between {min} and {max} characters")
                @Pattern(regexp = "^[\\w\\s-]+$", message = "Tag can only contain letters, numbers, spaces, and hyphens")
                String> names
) {
}
