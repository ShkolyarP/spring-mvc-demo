package com.paltvlad.repositories;

import com.paltvlad.data.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class Products {

    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>(Arrays.asList(
                new Product(1L, "Bread", 7.8),
                new Product(2L, "Milk", 18.9),
                new Product(3L, "Eggs", 23.3),
                new Product(4L, "Water", 5.5),
                new Product(5L, "Apples", 13.0)
        ));
    }

    public Product findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow(() -> new RuntimeException());
    }

    public List<Product> getALlProducts() {
        return Collections.unmodifiableList(products);
    }

    public void addToRepository(Product product) {
        products.add(product);
    }


}
