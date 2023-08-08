package com.zakaria.ecom.controllers;

import com.zakaria.ecom.dtos.request.CategoryRequest;
import com.zakaria.ecom.models.Category;
import com.zakaria.ecom.models.User;
import com.zakaria.ecom.services.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("api/v1/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping()
    public List<Category> getAllCategories() {
        return categoryService.getAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Long id) {
            Category category = categoryService.get(id);
            if (category == null) {
                return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        return  new ResponseEntity<>(category, HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<Category> addCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryRequest,category);
        Category category1 = categoryService.add(category);
        return  new ResponseEntity<>(category1, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Category> editCategory(@RequestBody CategoryRequest categoryRequest,@PathVariable Long id) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryRequest,category);
        Category category1 = categoryService.update(id,category);
        if (category1 == null) {
            return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(category1, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategoty(@PathVariable Long id) {
      boolean check =  categoryService.delete(id);
         if(check){
             return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
         }
        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

}
