package com.zakaria.ecom.mappers;

import com.zakaria.ecom.dtos.request.CategoryRequestDto;
import com.zakaria.ecom.dtos.response.CategoryResponseDto;
import com.zakaria.ecom.models.Category;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    private final ModelMapper modelMapper;

    public CategoryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CategoryResponseDto toDto(Category category) {
        return modelMapper.map(category, CategoryResponseDto.class);
    }

    public Category toEntity(CategoryRequestDto categoryDto) {
        return modelMapper.map(categoryDto, Category.class);
    }
}
