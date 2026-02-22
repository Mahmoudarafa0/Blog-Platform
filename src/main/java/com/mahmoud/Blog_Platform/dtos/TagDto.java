package com.mahmoud.Blog_Platform.dtos;

import java.util.UUID;

public record TagDto(
        UUID id,
        String name,
        Integer postCount
) {
}
