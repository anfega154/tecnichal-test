package com.ada.tecnichal_test.adapter.controller;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ValidationErrorResponse is a class that represents the structure of the error response
 * returned when validation errors occur in the application.
 */
@Getter
@Setter
public class ValidationErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String path;
    private List<String> details;

    public ValidationErrorResponse(LocalDateTime timestamp, int status, String error, String path, List<String> details) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.path = path;
        this.details = details;
    }
}