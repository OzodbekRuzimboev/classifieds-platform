package com.ozodbek.classifieds.listing.dto;

import com.ozodbek.classifieds.category.Category;
import tools.jackson.databind.JsonNode;

import java.math.BigDecimal;

public record CreateListingRequest(
        String title,
        BigDecimal price,
        Long categoryId,
        JsonNode attributes
) {
}
