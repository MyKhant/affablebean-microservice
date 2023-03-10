package com.example.apisecurity.exception;

import com.example.apisecurity.data.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundException extends ResponseStatusException {

    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND, "User not found from Token!");
    }
}
