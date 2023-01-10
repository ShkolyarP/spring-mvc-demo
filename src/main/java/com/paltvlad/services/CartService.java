package com.paltvlad.services;

import com.paltvlad.converters.ProductConverter;
import com.paltvlad.dto.ProductDto;
import com.paltvlad.entities.Product;
import com.paltvlad.exeptions.ResourceNotFoundException;
import com.paltvlad.models.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private final ProductConverter productConverter;


    private final Cart cart;

    public List<ProductDto> addToCard(Long id) {
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
        ProductDto productDto = productConverter.EntityToDto(product);
        cart.addToCart(productDto);
        return cart.getUserCart();

    }
}
