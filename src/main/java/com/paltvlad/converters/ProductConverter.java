package com.paltvlad.converters;

import com.paltvlad.dto.ProductDto;
import com.paltvlad.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    public Product dtoToEntity(ProductDto productDto){
        return new Product(productDto.getId(), productDto.getTitle(), productDto.getPrice(), productDto.getCategory());
    }

    public ProductDto EntityToDto(Product product){
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice(), product.getCategory());
    }

}
