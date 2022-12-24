package com.paltvlad.repositories;

import com.paltvlad.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceGreaterThanEqual(double minPrice);

    List<Product> findAllByIdLessThanEqual(Long maxId);

    List<Product> findAllByIdBetweenAndPriceGreaterThan(Long minId, Long maxId, double minPrice);

    List<Product> findAllByPriceBetween(double minPrice, double maxPrice);

    @Query("select p from Product p where p.id> :min")
    List<Product> myCustomFinder(Long min);
}
