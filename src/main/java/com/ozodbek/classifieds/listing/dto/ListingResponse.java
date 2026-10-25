package com.ozodbek.classifieds.listing.dto;

import com.ozodbek.classifieds.category.Category;
import com.ozodbek.classifieds.listing.Listing;
import tools.jackson.databind.JsonNode;

import java.math.BigDecimal;

public record ListingResponse(
        Long id,
        String title,
        BigDecimal price,
        Long categoryId,
        JsonNode attributes
) {
    public static ListingResponse from(Listing listing) {
        return new ListingResponse(
                listing.getId(),
                listing.getTitle(),
                listing.getPrice(),
                listing.getCategory().getId(),
                listing.getAttributes()
        );
    }
}
