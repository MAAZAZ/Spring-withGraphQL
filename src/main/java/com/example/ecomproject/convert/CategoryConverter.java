package com.example.ecomproject.convert;

import com.example.ecomproject.dto.CategoryDTO;
import com.example.ecomproject.models.CategoryModel;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {
    public CategoryDTO converter(final CategoryModel categoryModel) {
        return new CategoryDTO(categoryModel.getId(), categoryModel.getName());
    }
}
