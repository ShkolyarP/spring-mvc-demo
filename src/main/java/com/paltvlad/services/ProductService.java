package com.paltvlad.services;

import com.paltvlad.data.Product;
import com.paltvlad.repositories.Products;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class ProductService {
    private Products products;


    public ProductService(Products products) {
        this.products = products;
    }

    public void deleteById(Long id) {
        products.deleteById(id);
    }

    public List<Product> getALlProducts() {
        return products.getALlProducts();
    }

    public void changePrice(Long id, Integer percent) {
        Product product = products.findById(id);

        product.setPrice(new BigDecimal(product.getPrice() + product.getPrice() * percent / 100).setScale(2, RoundingMode.HALF_EVEN).doubleValue());

    }
}
