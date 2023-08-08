package com.zakaria.ecom.controllers;

import com.zakaria.ecom.dtos.request.CategoryRequest;
import com.zakaria.ecom.dtos.request.ProductRequest;
import com.zakaria.ecom.models.Category;
import com.zakaria.ecom.models.Product;
import com.zakaria.ecom.services.CategoryService;
import com.zakaria.ecom.services.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("api/v1/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Product product = productService.get(id);
        if (product == null) {
            return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(product, HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody ProductRequest productRequest) {
        Category category = categoryService.get(Long.valueOf(productRequest.getCategory()));
        Product product = new Product();
        BeanUtils.copyProperties(productRequest,product);
        product.setCategory(category);
        Product product1 = productService.add(product);
        return  new ResponseEntity<>(product1, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> editProduct(@RequestBody ProductRequest productRequest,@PathVariable Long id) {
        Product product = new Product();
        Category category = categoryService.get(Long.valueOf(productRequest.getCategory()));
        BeanUtils.copyProperties(productRequest,product);
        product.setCategory(category);
        Product product1 = productService.update(id,product);
        if (product1 == null) {
            return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(product1, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id) {
        boolean check = productService.delete(id);
        if(check){
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
}
