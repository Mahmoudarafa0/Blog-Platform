package com.mahmoud.Blog_Platform.dtos;

public record AuthResponse(
        String token,
        long expiresIn
) {
}
