package com.mahmoud.Blog_Platform.initilizer;

import com.mahmoud.Blog_Platform.entities.User;
import com.mahmoud.Blog_Platform.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class InitDatabase implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String ... args) throws Exception {

        User  user = User.builder()
                .email("admin@gmail.com")
                .name("Admin User")
                .password(passwordEncoder.encode("password"))
                .build();
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return;
        }
        userRepository.save(user);
        log.info("database has been initialized");
    }
}
