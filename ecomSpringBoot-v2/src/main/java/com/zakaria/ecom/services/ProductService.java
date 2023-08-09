package com.zakaria.ecom.services;

import com.zakaria.ecom.dtos.request.ProductRequestDto;
import com.zakaria.ecom.models.Product;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    Product getProduct(String id);
    Product addProduct(ProductRequestDto product) throws IOException;
    Product editProduct(String id, ProductRequestDto product);
    boolean deleteProduct(String id);

    // List<Product> getProductsByCategory(String id);

    // private searchProduct()
}
