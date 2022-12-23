package com.paltvlad.services;

import com.paltvlad.data.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    Optional<Product> findById(Long id);
    Optional<Product> findByTitle(String title);
    List<Product>findAll();

    void saveOrUpdate(Product product);
    void deleteById(Long id);

}
