package com.paltvlad.controllers;


import com.paltvlad.converters.ProductConverter;
import com.paltvlad.dto.ProductDto;
import com.paltvlad.exeptions.ResourceNotFoundException;
import com.paltvlad.entities.Product;
import com.paltvlad.services.ProductService;

import com.paltvlad.validators.ProductValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final ProductConverter productConverter;
    private final ProductValidator productValidator;

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

        return productService.findAll(minPrice, maxPrice, title_part, page).map(
                p -> productConverter.EntityToDto(p)
        );
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
        return productConverter.EntityToDto(product);
    }


    @PostMapping
    public ProductDto saveNewProduct(@RequestBody ProductDto productDto) {

        productValidator.validate(productDto);
        Product product = productConverter.dtoToEntity(productDto);

        product.setId(null);
        product = productService.save(product);
        return productConverter.EntityToDto(product);

    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productService.update(productDto);

        return productConverter.EntityToDto(product);
    }


}
