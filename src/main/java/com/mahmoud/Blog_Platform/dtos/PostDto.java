package com.mahmoud.Blog_Platform.dtos;

import com.mahmoud.Blog_Platform.entities.enums.PostStatus;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record PostDto(
        UUID id,
        String title,
        String content,
        AuthorDto author,
        CategoryDto category,
        Set<TagDto> tags,
        Integer readingTime,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        PostStatus status
) {
}
