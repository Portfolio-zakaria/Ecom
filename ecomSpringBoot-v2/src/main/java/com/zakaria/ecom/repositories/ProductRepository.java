package com.zakaria.ecom.repositories;

import com.zakaria.ecom.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
