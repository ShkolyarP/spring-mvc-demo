package com.paltvlad.services;

import com.paltvlad.model.Product;
import com.paltvlad.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Transactional
    public void saveNewProduct(String title, double price, Long category_id) {
        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        product.setCategory(categoryService.findById(category_id).get());
        if (product.getPrice() <= 0) {
            return;
        }
        productRepository.save(product);
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getALlProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public void changePrice(Long id, Integer percent) {
        Product product = productRepository.findById(id).get();

        product.setPrice(new BigDecimal(product.getPrice() + product.getPrice() * percent / 100).setScale(2, RoundingMode.HALF_EVEN).doubleValue());
        productRepository.save(product);
    }

    public List<Product> findByMinPrice(double minPrice) {
        return productRepository.findAllByPriceGreaterThanEqual(minPrice);
    }


    public List<Product> findByPrice(double min, double max) {
        return productRepository.findAllByPriceBetween(min, max);
    }
}
