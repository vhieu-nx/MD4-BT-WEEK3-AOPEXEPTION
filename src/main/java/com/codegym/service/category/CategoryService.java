package com.codegym.service.category;

import com.codegym.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryService implements ICategoryService{
    @Autowired
    private ICategoryService categoryService;
    @Override
    public List<Category> findAll() {
        return categoryService.findAll();
    }
}
