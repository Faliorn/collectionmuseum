package com.renegades.core.controllers;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renegades.core.api.AuthRequest;
import com.renegades.core.model.UserEntity;
import com.renegades.core.services.JwtService;
import com.renegades.core.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final ObjectProvider<UserService> userServiceProvider;
    private final ObjectProvider<JwtService> jwtServiceProvider;
    private final AuthenticationManager authenticationManager;

    AuthController(ObjectProvider<UserService> userServiceProvider, ObjectProvider<JwtService> jwtServiceProvider,
            AuthenticationManager authenticationManager) {
        this.userServiceProvider = userServiceProvider;
        this.jwtServiceProvider = jwtServiceProvider;
        this.authenticationManager = authenticationManager;
    }

    private UserService getUserService() {
        return userServiceProvider.getObject();
    }

    @PostMapping("/generateToken")
    public ResponseEntity<String> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            String token = jwtServiceProvider.getObject().generateToken(authRequest.getUsername());
            return ResponseEntity.ok(token);
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserEntity userRegistrationDto) {
        Boolean alreadyExists = true;
        try {
            getUserService().loadUserByUsername(userRegistrationDto.getUsername());
        } catch (UsernameNotFoundException unfe) {
            alreadyExists = false;
        }
        if (alreadyExists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

        UserEntity createdUser = getUserService().create(userRegistrationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }
}