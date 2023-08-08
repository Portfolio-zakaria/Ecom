package com.zakaria.ecom.services.impl;

import com.zakaria.ecom.dtos.request.CategoryRequest;
import com.zakaria.ecom.models.Category;
import com.zakaria.ecom.models.User;
import com.zakaria.ecom.repositories.CategoryRepository;
import com.zakaria.ecom.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        categoryRepository.findAll().forEach(c -> {
            if(c.getDeletedAt() == null){
                categories.add(c);
            }
        });
        return categories;
    }

    @Override
    public Category get(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category.getDeletedAt() == null){
            return category;
        }
        return  null;
    }

    @Override
    public Category add(Category category) {
        return  categoryRepository.save(category);
    }

    @Override
    public Category update(Long id, Category category) {
        Category dao = categoryRepository.findById(id).orElse(null);
        if(dao!=null){
            if(dao.getDeletedAt() == null){
                dao.setName(category.getName());
                return categoryRepository.save(dao);
            }
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
       Category category =  categoryRepository.findById(id).orElse(null);
       if(category != null){
           category.setDeletedAt(new Date());
           categoryRepository.save(category);
           return true;
       }
        return false;
    }
}
