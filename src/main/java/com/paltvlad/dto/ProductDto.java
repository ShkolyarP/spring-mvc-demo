package com.paltvlad.dto;

import com.paltvlad.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {


    private Long id;

    private String title;

    private double price;

    private Category category;

    private int amount = 1;
    private double sum;

    public ProductDto(Long id, String title, double price, Category category) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto that = (ProductDto) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
