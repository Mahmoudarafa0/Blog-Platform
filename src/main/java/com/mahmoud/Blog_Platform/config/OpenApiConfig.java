package com.mahmoud.Blog_Platform.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI blogApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Blog Platform API")
                        .version("1.0")
                        .description("API documentation for Blog Platform"));
    }
}