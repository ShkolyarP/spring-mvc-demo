package com.paltvlad.controllers;

import com.paltvlad.data.Product;
import com.paltvlad.repositories.Products;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    private Products products;

    public MainController(Products products) {
        this.products = products;
    }

    @GetMapping("/products")
    public String showProductsPage(Model model) {
        model.addAttribute("products", products.getALlProducts());
        return "products_page";
    }
    @RequestMapping(value="/add", method= RequestMethod.GET)
    public String addProductForm(Model model){
        model.addAttribute("product", new Product());
        return "add_product_form";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String addProductSubmit(@ModelAttribute Product product, Model model) {
        model.addAttribute("product", product);
        products.addToRepository(product);
        return "result";
    }



}
