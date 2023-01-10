package com.paltvlad.services;

import com.paltvlad.dto.ProductDto;
import com.paltvlad.exeptions.ResourceNotFoundException;
import com.paltvlad.entities.Product;
import com.paltvlad.repositories.ProductRepository;
import com.paltvlad.repositories.specifications.ProductsSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public Page<Product> findAll(Double minPrice, Double maxPrice, String partTitle, Integer page) {

        Specification<Product> spec = Specification.where(null);

        if (minPrice != null) {
            spec = spec.and(ProductsSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductsSpecifications.priceLessOrEqualsThan(maxPrice));
        }
        if (partTitle != null) {
            spec = spec.and(ProductsSpecifications.titleLike(partTitle));
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


    public Product save(Product product) {
        product.setCategory(categoryService.findById(1L).get());
        return productRepository.save(product);
    }

    @Transactional
    public Product update(ProductDto productDto) {
        Product product = productRepository.findById(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Невозможно обновить продукт, не найден в базе, id: " + productDto.getId()));
        product.setPrice(product.getPrice());
        product.setTitle(product.getTitle());
        product.setCategory(productDto.getCategory());
        return product;
    }
}
