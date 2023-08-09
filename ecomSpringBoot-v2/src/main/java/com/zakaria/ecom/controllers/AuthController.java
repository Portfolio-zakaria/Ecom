package com.zakaria.ecom.controllers;

import com.zakaria.ecom.dtos.request.LoginRequest;
import com.zakaria.ecom.dtos.request.SignupRequest;
import com.zakaria.ecom.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody @Valid SignupRequest signupRequest) {
        authService.signup(signupRequest);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
