package com.zakaria.ecom.services;

import com.zakaria.ecom.models.AppRole;
import com.zakaria.ecom.models.AppUser;

public interface AccountService {
    AppUser addNewUser(String username, String password, String email, String confirmPassword);
    AppRole addNewRole(String role);

    void attachRoleToUser(String userName, String role);
    void dettachRoleFromuser(String userName, String role);

    AppUser loadUserByUsername(String username);
}
