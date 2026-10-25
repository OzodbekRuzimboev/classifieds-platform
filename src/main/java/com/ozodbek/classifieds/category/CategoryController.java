package com.ozodbek.classifieds.category;

import com.ozodbek.classifieds.category.attribute.CategoryAttribute;
import com.ozodbek.classifieds.category.attribute.dto.AttributeResponse;
import com.ozodbek.classifieds.category.dto.CategoryResponse;
import com.ozodbek.classifieds.category.dto.CreateCategoryRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public CategoryResponse create(@RequestBody CreateCategoryRequest request) {
        return categoryService.create(request);
    }

    @GetMapping
    public List<CategoryResponse> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}/attributes")
    public List<AttributeResponse> getAttributes(@PathVariable Long id) {
        return categoryService.getCtegoryAttributes(id);
    }

}
