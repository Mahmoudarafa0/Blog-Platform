package com.mahmoud.Blog_Platform.services;

import com.mahmoud.Blog_Platform.entities.User;

import java.util.UUID;

public interface UserService {
    User findUserById(UUID userId);
}
