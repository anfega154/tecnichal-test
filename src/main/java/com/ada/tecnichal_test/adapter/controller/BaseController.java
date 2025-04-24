package com.ada.tecnichal_test.adapter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.Map;

public abstract class BaseController {

    /**
     * Creates a response entity with a 200 OK status and the given body.
     *
     * @param body
     */
    protected ResponseEntity<Object> ok(Object body) {
        return ResponseEntity.ok(Map.of(
                "timestamp", Instant.now().toString(),
                "data", body
        ));
    }

    /**
     * Creates a response entity with a 201 Created status and the given body.
     *
     * @param body
     */
    protected ResponseEntity<Object> created(Object body) {
        return ResponseEntity.status(201).body(Map.of(
                "timestamp", Instant.now().toString(),
                "data", body
        ));
    }

    /**
     * Creates a response entity with an error status.
     */
    protected ResponseEntity<Object> error(String message, HttpStatus status) {
        return ResponseEntity.status(status).body(Map.of(
                "timestamp", Instant.now().toString(),
                "status", status,
                "error", message
        ));
    }

    /**
     * Creates a response entity with a 404 Not Found status and the given message.
     *
     * @param message
     */
    protected ResponseEntity<Object> notFound(String message) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                "timestamp", Instant.now().toString(),
                "status", HttpStatus.NOT_FOUND,
                "error", message
        ));
    }
}