package com.paltvlad.repositories.specifications;

import com.paltvlad.model.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductsSpecifications {

    public static Specification<Product> priceGreaterOrEqualsThan(Double priceMin) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), priceMin);
    }

    public static Specification<Product> priceLessOrEqualsThan(Double priceMax) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), priceMax);
    }

    public static Specification<Product> titleLike(String title) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", title));
    }


}
