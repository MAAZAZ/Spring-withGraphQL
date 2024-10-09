package com.example.ecomproject.convert;

import com.example.ecomproject.dto.ProductDTO;
import com.example.ecomproject.models.CategoryModel;
import com.example.ecomproject.models.ProductModel;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    public ProductDTO converter(final ProductModel productModel) {
        final CategoryModel categoryModel = productModel.getCategory();
        return new ProductDTO(productModel.getId(), productModel.getName(), productModel.getPrice(),
                productModel.getCurrency(), productModel.getQuantity(),
                null, categoryModel != null ? categoryModel.getName() : null);
    }
}