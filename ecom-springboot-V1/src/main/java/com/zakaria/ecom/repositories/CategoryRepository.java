package com.zakaria.ecom.repositories;

import com.zakaria.ecom.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
