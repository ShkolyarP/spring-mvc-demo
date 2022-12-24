package com.paltvlad.controllers;

import com.paltvlad.model.Category;
import com.paltvlad.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("categories/{id}")
    @ResponseBody
    public Category showCategoryById(@PathVariable Long id) {
        return categoryService.findByIdWithProducts(id).get();
    }

}
