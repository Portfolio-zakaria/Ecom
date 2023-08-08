package com.zakaria.ecom.services;

import com.zakaria.ecom.dtos.request.UserRequest;
import com.zakaria.ecom.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getAll(int page, int size);
    User get(Long id);
    User add(UserRequest userRequest);
    User update(Long id , User user);
    boolean delete(Long id);
}
