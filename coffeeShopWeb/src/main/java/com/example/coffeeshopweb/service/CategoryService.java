package com.example.coffeeshopweb.service;

import com.example.coffeeshopweb.model.entity.Category;
import com.example.coffeeshopweb.model.entity.enums.CategoryNameEnum;

public interface CategoryService {
    void initCategories();

    Category findByCategoryNameEnum(CategoryNameEnum category);
}
