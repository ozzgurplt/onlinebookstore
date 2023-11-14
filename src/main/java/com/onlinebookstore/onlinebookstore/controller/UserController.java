package com.onlinebookstore.onlinebookstore.controller;

import com.onlinebookstore.onlinebookstore.entities.User;
import com.onlinebookstore.onlinebookstore.entities.rest.JwtAuthenticationResponse;
import com.onlinebookstore.onlinebookstore.entities.rest.LoginRequest;
import com.onlinebookstore.onlinebookstore.security.JwtTokenProvider;
import com.onlinebookstore.onlinebookstore.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;


import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {


    private UserService userService;
    private JwtTokenProvider jwtTokenProvider;

    private AuthenticationManager authenticationManager;
    private JwtAuthenticationResponse jwtAuthenticationResponse;

    public UserController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody User user, Set<String> roleNames) {
        User newUser = userService.signUp(user,roleNames);
        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken(loginRequest.getUsernameOrEmail());
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }


}
