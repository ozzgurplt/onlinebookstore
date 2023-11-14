package com.onlinebookstore.onlinebookstore.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long userId) {
        super("User not found with ID: " + userId);
    }
}
