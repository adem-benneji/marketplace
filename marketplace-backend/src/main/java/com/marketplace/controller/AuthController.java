package com.marketplace.controller;

import com.marketplace.dto.AuthResponse;
import com.marketplace.dto.LoginRequest;
import com.marketplace.dto.RegisterRequest;
import com.marketplace.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // ---------------- REGISTER ----------------
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        System.out.println("REGISTER HIT: " + request.getUsername());
        AuthResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    // ---------------- LOGIN ----------------
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}
