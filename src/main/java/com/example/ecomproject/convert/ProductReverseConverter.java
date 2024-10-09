package com.example.ecomproject.convert;

import com.example.ecomproject.dto.ProductDTO;
import com.example.ecomproject.models.ProductModel;
import com.example.ecomproject.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductReverseConverter {

    private final CategoryRepository categoryRepository;

    public ProductModel converter(final ProductDTO productDTO) {
        final ProductModel productModel = ProductModel.builder()
                .id(productDTO.id())
                .name(productDTO.name())
                .price(productDTO.price())
                .currency(productDTO.currency())
                .quantity(productDTO.quantity())
                .build();
        categoryRepository.findById(productDTO.categoryId())
                .ifPresent(productModel::setCategory);
        return productModel;
    }
}
