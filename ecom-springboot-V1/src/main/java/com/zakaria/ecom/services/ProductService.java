package com.zakaria.ecom.services;


import com.zakaria.ecom.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService {
    List<Product> getAll();
    Product get(Long id);
    Product add(Product product);
    Product update(Long id ,Product product);
    boolean delete(Long id);
}
