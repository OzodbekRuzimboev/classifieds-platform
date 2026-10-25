package com.ozodbek.classifieds.category.attribute.dto;

import com.ozodbek.classifieds.category.attribute.AttributeType;
import com.ozodbek.classifieds.category.attribute.CategoryAttribute;

public record AttributeResponse(
        String name,
        AttributeType type,
        boolean required
) {
    public static AttributeResponse from(CategoryAttribute attribute) {
        return new AttributeResponse(attribute.getName(), attribute.getAttributeType(), attribute.isRequired());
    }
}
