package com.zakaria.ecom.controllers;


import com.zakaria.ecom.dtos.request.ProductRequestDto;
import com.zakaria.ecom.dtos.response.ProductResponseDto;
import com.zakaria.ecom.mappers.ProductMapper;
import com.zakaria.ecom.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@Slf4j
public class ProductController {
    private ProductMapper productMapper;
    private ProductService productService;

    public ProductController(ProductMapper productMapper, ProductService productService) {
        this.productMapper = productMapper;
        this.productService = productService;
    }
    @PostMapping()
    public ResponseEntity<ProductResponseDto> save(@Valid @RequestBody ProductRequestDto requestDTO) throws IOException {
        return new ResponseEntity<>(productMapper.toDto(productService.addProduct(requestDTO)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> edit(@Valid @RequestBody ProductRequestDto requestDTO, @PathVariable String id){
        return new ResponseEntity<>(productMapper.toDto(productService.editProduct(id, requestDTO)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> get(@PathVariable String id){
        return new ResponseEntity<>(productMapper.toDto(productService.getProduct(id)), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponseDto>> getAll () {
        List<ProductResponseDto> categories = productService.getProducts().stream().map(productMapper::toDto).collect(Collectors.toList());
        return  ResponseEntity.ok(categories);
    }
    
    
}
