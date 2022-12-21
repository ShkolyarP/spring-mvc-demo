package com.paltvlad.controllers;


import com.paltvlad.services.ProductService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {

    private ProductService productService;

    public MainController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/customer/{id}")
    public void orderListByCustomers(@PathVariable Long id) {
        productService.orderListByCustomers(id);
    }



//    @GetMapping("/products")
//    public List<Product> getAllProducts() {
//        return productService.getALlProducts();
//    }
//
//    @GetMapping("/products/delete/{id}")
//    public void deleteById(@PathVariable Long id) {
//        productService.deleteById(id);
//    }
//
//    @GetMapping("/products/change_price")
//    public void changePrice(@RequestParam Long id, @RequestParam Integer percent) {
//        productService.changePrice(id, percent);
//    }

}
