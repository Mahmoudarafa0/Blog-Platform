package com.mahmoud.Blog_Platform.services.impl;

import com.mahmoud.Blog_Platform.dtos.AuthResponse;
import com.mahmoud.Blog_Platform.dtos.LoginRequest;
import com.mahmoud.Blog_Platform.security.JwtUtil;
import com.mahmoud.Blog_Platform.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Override
    public AuthResponse authenticate(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.email(),
                        loginRequest.password()
                )
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.email());
        String token = jwtUtil.generateToken(userDetails);
        return new AuthResponse(token, 24*60*60*1000);
    }
}
