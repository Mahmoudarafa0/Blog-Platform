package com.mahmoud.Blog_Platform.dtos;

import java.util.UUID;

public record AuthorDto(
        UUID id,
        String name
) {
}
