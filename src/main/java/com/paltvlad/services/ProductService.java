package com.paltvlad.services;

import com.paltvlad.model.Product;
import com.paltvlad.repositories.ProductRepository;
import com.paltvlad.repositories.specifications.ProductsSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

    public Page<Product> find(Double minPrice, Double maxPrice, String title_part, Integer page) {

        Specification<Product> spec = Specification.where(null);

        if (minPrice != null) {
            spec = spec.and(ProductsSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductsSpecifications.priceLessOrEqualsThan(maxPrice));
        }
        if (title_part != null) {
            spec = spec.and(ProductsSpecifications.titleLike(title_part));
        }

        return productRepository.findAll(spec, PageRequest.of(page - 1, 10));
    }


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


    public Product save(Product product) {
        product.setCategory(categoryService.findById(1L).get());
        return productRepository.save(product);
    }
}
