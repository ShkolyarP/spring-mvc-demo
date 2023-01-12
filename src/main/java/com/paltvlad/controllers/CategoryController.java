package com.paltvlad.controllers;

import com.paltvlad.entities.Category;
import com.paltvlad.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    @ResponseBody
    public Category showCategoryById(@PathVariable Long id) {
        return categoryService.findByIdWithProducts(id).get();
    }

}
