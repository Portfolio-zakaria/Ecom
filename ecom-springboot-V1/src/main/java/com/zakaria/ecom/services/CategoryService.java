package com.zakaria.ecom.services;

import com.zakaria.ecom.dtos.request.CategoryRequest;
import com.zakaria.ecom.models.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    List<Category> getAll();
    Category get(Long id);
    Category add(Category category);
    Category update(Long id ,Category category);
    boolean delete(Long id);

}
