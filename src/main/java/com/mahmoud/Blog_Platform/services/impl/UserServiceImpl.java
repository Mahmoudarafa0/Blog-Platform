package com.mahmoud.Blog_Platform.services.impl;

import com.mahmoud.Blog_Platform.entities.User;
import com.mahmoud.Blog_Platform.repositories.UserRepository;
import com.mahmoud.Blog_Platform.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findUserById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + userId + " not found"));
    }
}
