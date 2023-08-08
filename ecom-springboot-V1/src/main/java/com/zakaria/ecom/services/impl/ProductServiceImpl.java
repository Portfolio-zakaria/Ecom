package com.zakaria.ecom.services.impl;

import com.zakaria.ecom.models.Category;
import com.zakaria.ecom.models.Product;
import com.zakaria.ecom.repositories.ProductRepository;
import com.zakaria.ecom.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(p -> {
            if(p.getDeletedAt() == null){
                products.add(p);
            }
        });
        return products;
    }

    @Override
    public Product get(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product.getDeletedAt() == null){
            return product;
        }
        return  null;
    }

    @Override
    public Product add(Product product) {
       return  productRepository.save(product);

    }

    @Override
    public Product update(Long id, Product product) {
        Product dao = productRepository.findById(id).orElse(null);
        if(dao!=null){
            if(dao.getDeletedAt() == null){
                dao.setName(product.getName());
                dao.setDescription(product.getDescription());
                dao.setPrice(product.getPrice());
                dao.setQuantity(product.getQuantity());
                dao.setPhoto(product.getPhoto());
                dao.setCategory(product.getCategory());
                return productRepository.save(dao);
            }
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        Product product =  productRepository.findById(id).orElse(null);
        if(product != null){
            product.setDeletedAt(new Date());
            productRepository.save(product);
            return true;
        }
        return false;
    }
}
