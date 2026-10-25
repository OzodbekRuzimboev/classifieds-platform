package com.ozodbek.classifieds.category;

import com.ozodbek.classifieds.category.attribute.CategoryAttribute;
import com.ozodbek.classifieds.category.attribute.CategoryAttributeRepository;
import com.ozodbek.classifieds.category.attribute.dto.AttributeResponse;
import com.ozodbek.classifieds.category.dto.CategoryResponse;
import com.ozodbek.classifieds.category.dto.CreateCategoryRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryAttributeRepository attributeRepository;

    public CategoryService(CategoryRepository categoryRepository, CategoryAttributeRepository attributeRepository) {
        this.categoryRepository = categoryRepository;
        this.attributeRepository = attributeRepository;
    }

    public CategoryResponse create(CreateCategoryRequest request) {
        var category = new Category(request.name());

        categoryRepository.save(category);

        return CategoryResponse.from(category);
    }

    public List<CategoryResponse> getAll() {
        return categoryRepository.findAll().stream().map(CategoryResponse::from).toList();
    }

    public List<AttributeResponse> getCtegoryAttributes(Long id) {
        return attributeRepository.findAllByCategory_Id(id).stream().map(AttributeResponse::from).toList();
    }
}
