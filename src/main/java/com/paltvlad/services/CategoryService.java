package com.paltvlad.services;

import com.paltvlad.model.Category;
import com.paltvlad.model.Product;
import com.paltvlad.repositories.CategoryRepository;
import com.paltvlad.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Optional<Category> findByIdWithProducts(Long id){
        return categoryRepository.findCategoryWithProducts(id);
    }

    public Optional<Category> findById(Long id){
        return categoryRepository.findById(id);
    }

}
