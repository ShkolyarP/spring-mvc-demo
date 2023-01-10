package com.paltvlad.services;

import com.paltvlad.entities.Category;
import com.paltvlad.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
