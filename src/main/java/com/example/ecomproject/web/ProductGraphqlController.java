package com.example.ecomproject.web;

import com.example.ecomproject.convert.CategoryConverter;
import com.example.ecomproject.convert.ProductConverter;
import com.example.ecomproject.convert.ProductReverseConverter;
import com.example.ecomproject.dto.CategoryDTO;
import com.example.ecomproject.dto.ProductDTO;
import com.example.ecomproject.models.CategoryModel;
import com.example.ecomproject.models.ProductModel;
import com.example.ecomproject.repositories.CategoryRepository;
import com.example.ecomproject.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class ProductGraphqlController {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductConverter productConverter;
    private final ProductReverseConverter productReverseConverter;
    private final CategoryConverter categoryConverter;

    @QueryMapping
    public List<ProductDTO> productList() {
        final List<ProductDTO> productDTOList = new ArrayList<>();
        productRepository.findAll().forEach(
                productModel -> productDTOList.add(productConverter.converter(productModel)));
        return productDTOList;
    }

    @QueryMapping
    public ProductDTO getProduct(@Argument final Long id) throws RuntimeException {
        final ProductModel productModel = productRepository.findById(id).orElse(null);
        if (productModel != null) {
            return productConverter.converter(productModel);
        }
        throw new RuntimeException("Product not found");
    }

    @QueryMapping
    public CategoryDTO getCategory(@Argument final Long id) throws RuntimeException {
        final CategoryModel categoryModel = categoryRepository.findById(id).orElse(null);
        if (categoryModel != null) {
            return categoryConverter.converter(categoryModel);
        }
        throw new RuntimeException("Category not found");
    }

    @MutationMapping
    public ProductDTO addProduct(@Argument final ProductDTO product) {
        final ProductModel productModel = productReverseConverter.converter(product);
        final ProductModel productSaved = productRepository.save(productModel);
        return product.withId(productSaved.getId());
    }

    @MutationMapping
    public ProductDTO updateProduct(@Argument final Long id, @Argument ProductDTO product) {
        product = product.withId(id);
        final ProductModel productModel = productReverseConverter.converter(product);
        productRepository.save(productModel);
        return product;
    }

    @MutationMapping
    public void removeProduct(@Argument final Long id) {
        productRepository.deleteById(id);
    }
}