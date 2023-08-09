package com.zakaria.ecom.services.Impl;

import com.zakaria.ecom.dtos.request.ProductRequestDto;
import com.zakaria.ecom.enums.Status;
import com.zakaria.ecom.exceptions.ResourceNotFoundException;
import com.zakaria.ecom.mappers.ProductMapper;
import com.zakaria.ecom.models.Category;
import com.zakaria.ecom.models.Product;
import com.zakaria.ecom.repositories.CategoryRepository;
import com.zakaria.ecom.repositories.ProductRepository;
import com.zakaria.ecom.services.ProductService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Value("${upload.dir}")
    private String uploadDir;
    private CategoryRepository categoryRepository;

    private ProductRepository productRepository;

    private ProductMapper productMapper;

    public ProductServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository, ProductMapper productMapper) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<Product> getProducts() {

        return productRepository.findAll();
    }

    @Override
    public Product getProduct(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException( "Product not found with id " + id));

        return product;
    }

    @Override
    public Product addProduct(ProductRequestDto productRequestDto) throws IOException {
        Category category = categoryRepository.findById(productRequestDto.getCategory())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + productRequestDto.getCategory()));

        Product product = productMapper.toEntity(productRequestDto);
        String id =  UUID.randomUUID().toString();
        // Save the photo file
        String photoFileName = id + "_" + productRequestDto.getPhoto().getOriginalFilename();
        Path photoPath = Paths.get(uploadDir, photoFileName);
        Files.write(photoPath, productRequestDto.getPhoto().getBytes());

        product.setPhotoFileName(photoFileName);
        product.setCategory(category);
        product.setId(id);
        return productRepository.save(product);
    }

    @Override
    public Product editProduct(String id, ProductRequestDto productRequestDto) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException( "City not found with id " + id));
        Category category = categoryRepository.findById(productRequestDto.getCategory())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + productRequestDto.getCategory()));

        product.setCategory(category);
        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setPrice(productRequestDto.getPrice());
        product.setQuantity(productRequestDto.getQuantity());
        product.setStatus(productRequestDto.getStatus());
        return productRepository.save(product);
    }

    @Override
    public boolean deleteProduct(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException( "Product not found with id " + id));

        product.setDeletedAt(LocalDateTime.now());
        product.setStatus(Status.INACTIVE);
        productRepository.save(product);
        return true;
    }

}
