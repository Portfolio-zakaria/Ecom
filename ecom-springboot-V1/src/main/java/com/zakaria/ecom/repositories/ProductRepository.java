package com.zakaria.ecom.repositories;

import com.zakaria.ecom.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
