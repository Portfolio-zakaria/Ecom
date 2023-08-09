package com.zakaria.ecom.services;

import com.zakaria.ecom.dtos.request.LoginRequest;
import com.zakaria.ecom.dtos.request.SignupRequest;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface AuthService {

    ResponseEntity<Map<String, String>> login(LoginRequest loginRequest);

    boolean signup(SignupRequest signupRequest);
}
