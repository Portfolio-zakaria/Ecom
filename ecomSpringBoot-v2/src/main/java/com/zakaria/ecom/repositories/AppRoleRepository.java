package com.zakaria.ecom.repositories;

import com.zakaria.ecom.models.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, String> {
    AppRole findByName(String name);
}
