package com.zakaria.ecom.repositories;

import com.zakaria.ecom.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
