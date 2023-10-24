package com.example.coffeeshopweb.service.impl;

import com.example.coffeeshopweb.model.entity.Category;
import com.example.coffeeshopweb.model.entity.enums.CategoryNameEnum;
import com.example.coffeeshopweb.repository.CategoryRepository;
import com.example.coffeeshopweb.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initCategories() {

        if (categoryRepository != null) {
            return;
        }

        Arrays.stream(CategoryNameEnum.values())
            .forEach(categoryNameEnum -> {
                Category category = new Category();
                category.setName(categoryNameEnum);
                switch (categoryNameEnum) {
                    case CAKE -> category.setNeededTime(10);
                    case COFFEE -> category.setNeededTime(2);
                    case DRINK -> category.setNeededTime(1);
                    case OTHER -> category.setNeededTime(5);
                }
                categoryRepository.save(category);
            });

    }

    @Override
    public Category findByCategoryNameEnum(CategoryNameEnum category) {
        return categoryRepository.findCategoryByName(category).orElse(null);
    }
}

