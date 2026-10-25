package com.ozodbek.classifieds.category.attribute;

import com.ozodbek.classifieds.exception.InvalidAttributeException;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AttributeValidationService {

    private final CategoryAttributeRepository repository;

    public AttributeValidationService(CategoryAttributeRepository repository) {
        this.repository = repository;
    }

    public void validate(Long categoryId, JsonNode attributes) {

        if (attributes == null || !attributes.isObject()) {
            throw new InvalidAttributeException("Attributes must be a JSON object.");
        }

        List<CategoryAttribute> list = repository.findAllByCategory_Id(categoryId);

        Map<String, CategoryAttribute> map = list.stream().collect(Collectors.toMap(
                CategoryAttribute::getName,
                Function.identity()
        ));

        for (Map.Entry<String, JsonNode> entry : attributes.properties()) {

            CategoryAttribute attribute = map.get(entry.getKey());

            if (attribute == null) {
                throw new InvalidAttributeException("Unknown attribute: " + entry.getKey());
            }

            if (!isValidType(entry.getValue(), attribute.getAttributeType())) {
                throw new InvalidAttributeException("Invalid type for attribute '" + entry.getKey()
                        + "'. Expected: " + attribute.getAttributeType());
            }
        }

        for (CategoryAttribute attribute : list) {

            if (!attribute.isRequired()) {
                continue;
            }

            String name = attribute.getName();

            if (!attributes.hasNonNull(name)) {
                throw new InvalidAttributeException("Required attribute '" + name + "' is missing.");
            }

            JsonNode value = attributes.get(name);

            if (value.isString() && value.asString().isBlank()) {
                throw new InvalidAttributeException("Required attribute '" + name + "' cannot be blank.");
            }
        }
    }

    private static boolean isValidType(JsonNode value, AttributeType type) {
        return switch (type) {
            case STRING -> value.isString();
            case INTEGER -> value.isIntegralNumber() && value.canConvertToInt();
            case DECIMAL -> value.isNumber();
            case BOOLEAN -> value.isBoolean();
        };
    }
}
