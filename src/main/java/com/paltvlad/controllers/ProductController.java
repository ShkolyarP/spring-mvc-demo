package com.paltvlad.controllers;


import com.paltvlad.dto.ProductDto;
import com.paltvlad.model.Category;
import com.paltvlad.model.Product;
import com.paltvlad.services.ProductService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Page<ProductDto> getAllProducts(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_price", required = false) Double minPrice,
            @RequestParam(name = "max_price", required = false) Double maxPrice,
            @RequestParam(name = "title_part", required = false) String title_part

    ) {
        if (page < 1) {
            page = 1;
        }

        return productService.find(minPrice, maxPrice, title_part, page).map(
                p -> new ProductDto(p)
        );
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id) {
        return productService.findById(id).get();
    }


    @PostMapping
    public ProductDto addProduct(@RequestBody Product product) {

        product.setId(null);
        return new ProductDto(productService.save(product));
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody Product product) {
        return new ProductDto(productService.save(product));
    }

//    @PatchMapping("/price")
//    public void changePrice(@RequestBody Long id, @RequestBody Integer percent) {
//        productService.changePrice(id, percent);
//    }
}
