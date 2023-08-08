package com.zakaria.ecom.controllers;

import com.zakaria.ecom.dtos.UserDto;
import com.zakaria.ecom.dtos.request.RegisterDto;
import com.zakaria.ecom.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController()
@RequestMapping("api/v1")
public class AuthController {
    @Autowired
     AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody @Valid RegisterDto registerDto) {
        return new ResponseEntity(authService.register(registerDto), HttpStatus.CREATED);
    }
}
