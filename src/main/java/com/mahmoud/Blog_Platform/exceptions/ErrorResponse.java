package com.mahmoud.Blog_Platform.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class ErrorResponse {
    private int status;
    private String message;
    private List<FieldError> errors;

    @AllArgsConstructor
    @Getter
    @Builder
    public static class FieldError {
        private int status;
        private String message;
        private String field;
    }
}
