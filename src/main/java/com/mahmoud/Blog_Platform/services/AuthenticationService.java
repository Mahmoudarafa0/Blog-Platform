package com.mahmoud.Blog_Platform.services;

import com.mahmoud.Blog_Platform.dtos.AuthResponse;
import com.mahmoud.Blog_Platform.dtos.LoginRequest;

public interface AuthenticationService {
    AuthResponse authenticate(LoginRequest loginRequest);
}
