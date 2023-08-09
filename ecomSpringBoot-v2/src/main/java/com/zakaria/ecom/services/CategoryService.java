package com.zakaria.ecom.services;

import com.zakaria.ecom.dtos.request.CategoryRequestDto;
import com.zakaria.ecom.models.Category;

import java.util.List;

public interface CategoryService {
    Category addCategory(CategoryRequestDto categoryRequestDTO);
    Category editCategory(String id , CategoryRequestDto categoryRequestDTO);
    Category getCategory(String id);
    List<Category> getCategories();
    boolean deleteCategory(String id);

}
