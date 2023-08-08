package com.zakaria.ecom.services.impl;

import com.zakaria.ecom.models.Category;
import com.zakaria.ecom.models.Role;
import com.zakaria.ecom.repositories.RoleRepository;
import com.zakaria.ecom.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public List<Role> getAll() {
        List<Role> roles = new ArrayList<>();
        roleRepository.findAll().forEach(c -> {
            if(c.getDeletedAt() == null){
                roles.add(c);
            }
        });
        return roles;
    }

    @Override
    public Role get(Long id) {
        Role role = roleRepository.findById(id).orElse(null);
        if (role.getDeletedAt() == null){
            return role;
        }
        return  null;
    }

    @Override
    public Role add(Role role) {
        return  roleRepository.save(role);
    }

    @Override
    public Role update(Long id, Role role) {
        Role dao = roleRepository.findById(id).orElse(null);
        if(dao!=null){
            if(dao.getDeletedAt() == null){
                dao.setName(role.getName());
                return roleRepository.save(dao);
            }
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
       Role role =  roleRepository.findById(id).orElse(null);
       if(role != null){
           role.setDeletedAt(new Date());
           roleRepository.save(role);
           return true;
       }
        return false;
    }
}
