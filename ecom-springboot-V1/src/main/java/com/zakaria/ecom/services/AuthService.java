package com.zakaria.ecom.services;

import com.zakaria.ecom.dtos.UserDto;
import com.zakaria.ecom.dtos.request.RegisterDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    UserDto register(RegisterDto registerDto);
}
