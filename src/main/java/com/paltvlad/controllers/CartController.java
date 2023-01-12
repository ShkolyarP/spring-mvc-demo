package com.paltvlad.controllers;


import com.paltvlad.dto.ProductDto;

import com.paltvlad.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping("/{id}")
    public List<ProductDto> addToCart(@PathVariable Long id) {


        return cartService.addToCard(id);
    }
}
