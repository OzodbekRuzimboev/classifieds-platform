package com.ozodbek.classifieds.category.dto;

import com.ozodbek.classifieds.category.Category;

public record CategoryResponse(
        Long id,
        String name
) {
    public static CategoryResponse from(Category category) {
        return new CategoryResponse(category.getId(), category.getName());
    }
}
