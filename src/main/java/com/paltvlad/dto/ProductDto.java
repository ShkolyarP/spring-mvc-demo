package com.paltvlad.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paltvlad.model.Category;
import com.paltvlad.model.Product;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ProductDto {


    private Long id;

    private String title;

    private double price;

    private Category category;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.category = product.getCategory();
    }

}
