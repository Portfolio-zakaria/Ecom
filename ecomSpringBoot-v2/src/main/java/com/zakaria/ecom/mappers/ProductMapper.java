package com.zakaria.ecom.mappers;

import com.zakaria.ecom.dtos.request.ProductRequestDto;
import com.zakaria.ecom.dtos.response.ProductResponseDto;
import com.zakaria.ecom.models.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ProductMapper {
    private final ModelMapper modelMapper;
    private final CategoryMapper categoryMapper;

    public ProductMapper(ModelMapper modelMapper, CategoryMapper categoryMapper) {
        this.modelMapper = modelMapper;
        this.categoryMapper = categoryMapper;
    }

    public ProductResponseDto toDto(Product product) {
        ProductResponseDto productDto = modelMapper.map(product, ProductResponseDto.class);
        productDto.setCategory(categoryMapper.toDto(product.getCategory()));
        return productDto;
    }

    public Product toEntity(ProductRequestDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        return product;
    }
}
