package com.zakaria.ecom.services.Impl;

import com.zakaria.ecom.dtos.request.CategoryRequestDto;
import com.zakaria.ecom.enums.Status;
import com.zakaria.ecom.exceptions.ResourceNotFoundException;
import com.zakaria.ecom.mappers.CategoryMapper;
import com.zakaria.ecom.models.Category;
import com.zakaria.ecom.repositories.CategoryRepository;
import com.zakaria.ecom.services.CategoryService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class CategoryServiceImpl implements CategoryService {


    private CategoryRepository categoryRepository;


    private CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Category addCategory(CategoryRequestDto categoryRequestDTO) {
        Category category = categoryMapper.toEntity(categoryRequestDTO);
        category.setId(UUID.randomUUID().toString());
        return categoryRepository.save(category);
    }

    @Override
    public Category editCategory(String id, CategoryRequestDto categoryRequestDTO) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException( "Category not found with id " + id));

        category.setName(categoryRequestDTO.getName());
        category.setStatus(categoryRequestDTO.getStatus());
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategory(String id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException( "Category not found with id " + id));

        return category;
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public boolean deleteCategory(String id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException( "Category not found with id " + id));

        category.setDeletedAt(LocalDateTime.now());
        category.setStatus(Status.INACTIVE);
        categoryRepository.save(category);
        return true;
    }
}
