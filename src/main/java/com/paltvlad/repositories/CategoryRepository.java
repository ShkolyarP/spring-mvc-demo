package com.paltvlad.repositories;

import com.paltvlad.model.Category;
import com.paltvlad.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select c from Category c join fetch c.products where c.id = :id")
    Optional<Category> findCategoryWithProducts(Long id);
}
