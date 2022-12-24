package com.paltvlad.controllers;


import com.paltvlad.model.Product;
import com.paltvlad.services.ProductService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getALlProducts();
    }

    @GetMapping("/products/{id}")
    public Product findById(@PathVariable Long id) {
        return productService.findById(id).get();
    }

    @GetMapping("/products/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @GetMapping("/products/change_price")
    public void changePrice(@RequestParam Long id, @RequestParam Integer percent) {
        productService.changePrice(id, percent);
    }

    @GetMapping("/products/find_by_min_price")
    public List<Product> findByMinPrice(@RequestParam(name = "min") double min) {
        return productService.findByMinPrice(min);
    }

    @GetMapping("/products/find_by_price")
    public List<Product> findByPrice(@RequestParam(name = "min", defaultValue = "0") double min, @RequestParam(name = "max", defaultValue = "1000000000000") double max) {
        return productService.findByPrice(min, max);
    }


    @PostMapping("/products")
    public void addProduct(@RequestParam(name = "title") String title, @RequestParam(name = "price") Double price, @RequestParam(name = "category_id", defaultValue = "1") Long id) {
        productService.saveNewProduct(title, price, id);
    }

}
