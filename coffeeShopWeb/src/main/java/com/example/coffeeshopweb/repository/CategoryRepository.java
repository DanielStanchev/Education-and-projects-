package com.example.coffeeshopweb.repository;

import com.example.coffeeshopweb.model.entity.Category;
import com.example.coffeeshopweb.model.entity.enums.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    Optional<Category> findCategoryByName(CategoryNameEnum name);

}
