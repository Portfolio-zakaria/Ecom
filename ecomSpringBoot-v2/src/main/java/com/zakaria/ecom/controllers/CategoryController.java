package com.zakaria.ecom.controllers;

import com.zakaria.ecom.dtos.request.CategoryRequestDto;
import com.zakaria.ecom.dtos.response.CategoryResponseDto;
import com.zakaria.ecom.mappers.CategoryMapper;
import com.zakaria.ecom.services.CategoryService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
@Slf4j
public class CategoryController {
    private CategoryMapper categoryMapper;
    private CategoryService categoryService;
    public CategoryController (CategoryMapper categoryMapper, CategoryService categoryService) {
        this.categoryMapper = categoryMapper;
        this.categoryService = categoryService;
    }

    @PostMapping()
    public ResponseEntity<CategoryResponseDto> save(@Valid @RequestBody CategoryRequestDto requestDTO){
        return new ResponseEntity<>(categoryMapper.toDto(categoryService.addCategory(requestDTO)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> edit(@Valid @RequestBody CategoryRequestDto requestDTO, @PathVariable String id){
        return new ResponseEntity<>(categoryMapper.toDto(categoryService.editCategory(id, requestDTO)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> get(@PathVariable String id){
        return new ResponseEntity<>(categoryMapper.toDto(categoryService.getCategory(id)), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<CategoryResponseDto>> getAll () {
        List<CategoryResponseDto> categories = categoryService.getCategories().stream().map(categoryMapper::toDto).collect(Collectors.toList());
        return  ResponseEntity.ok(categories);
    }
}
