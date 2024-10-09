package com.example.ecomproject.dto;

public record ProductDTO(Long id, String name,
                         double price, String currency,
                         int quantity, Long categoryId,
                         String categoryName)
{
    public ProductDTO withId(final Long id) {
        return new ProductDTO(id, name(), price(), currency(), quantity(), categoryId(), categoryName());
    }
}