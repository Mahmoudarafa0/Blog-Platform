package com.mahmoud.Blog_Platform.dtos;

import java.util.UUID;

public record CategoryDto(
        UUID id,
        String name,
        long postCount
) {
}
