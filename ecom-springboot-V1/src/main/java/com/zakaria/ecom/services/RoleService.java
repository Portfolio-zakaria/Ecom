package com.zakaria.ecom.services;

import com.zakaria.ecom.models.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {

    List<Role> getAll();
    Role get(Long id);
    Role add(Role role);
    Role update(Long id ,Role role);
    boolean delete(Long id);

}
