package com.panicatthedevops.campuscarebackend.controller;

import com.panicatthedevops.campuscarebackend.security.AuthRequest;
import com.panicatthedevops.campuscarebackend.security.JwtUtil;
import com.panicatthedevops.campuscarebackend.service.CustomUserDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;

    public AuthController(JwtUtil jwtUtil, AuthenticationManager authenticationManager, CustomUserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> createToken(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            return new ResponseEntity<>("Bad credentials.", HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(jwtUtil.generateToken(userDetailsService.loadUserByUsername(authRequest.getUsername())));
    }
}
