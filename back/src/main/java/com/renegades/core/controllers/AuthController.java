package com.renegades.core.controllers;

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
import com.renegades.core.services.JwtService;
import com.renegades.core.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService service;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    AuthController(UserService service, JwtService jwtService,
            AuthenticationManager authenticationManager) {
        this.service = service;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    /*
     * @PostMapping("/register")
     * public ResponseEntity<String> addNewUser(@RequestBody UserInfo userInfo) {
     * String response = service.addUser(userInfo);
     * return ResponseEntity.status(HttpStatus.CREATED).body(response);
     * }
     */

    @PostMapping("/generateToken")
    public ResponseEntity<String> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(authRequest.getUsername());
            return ResponseEntity.ok(token);
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    /*
     * 
     * @Autowired
     * private AuthenticationManager authenticationManager;
     * 
     * @Autowired
     * private CustomUserDetailsService userDetailsService;
     * 
     * @Autowired
     * private JwtUtil jwtUtil;
     * 
     * @PostMapping
     * public ResponseEntity<?> createAuthenticationToken(@RequestBody
     * AuthenticationRequest authenticationRequest)
     * throws Exception {
     * 
     * Authentication authentication = authenticationManager.authenticate(
     * new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
     * authenticationRequest.getPassword()));
     * 
     * final UserDetails userDetails =
     * userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
     * final String jwt = jwtUtil.generateToken(userDetails);
     * 
     * return ResponseEntity.ok(new AuthenticationResponse(jwt));
     * }
     */
}