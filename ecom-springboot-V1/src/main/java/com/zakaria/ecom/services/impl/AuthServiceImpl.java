package com.zakaria.ecom.services.impl;

import com.zakaria.ecom.dtos.UserDto;
import com.zakaria.ecom.dtos.request.RegisterDto;
import com.zakaria.ecom.models.User;
import com.zakaria.ecom.repositories.UserRepository;
import com.zakaria.ecom.services.AuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto register(RegisterDto registerDto) {
        System.out.println("service "+ registerDto.getEmail());
        User user = new User();
        BeanUtils.copyProperties(registerDto, user);
        UserDto dto = new UserDto();

        BeanUtils.copyProperties( userRepository.save(user) ,dto);
        return dto;
    }
}
