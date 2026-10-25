package com.ozodbek.classifieds.category.attribute;

import com.ozodbek.classifieds.category.Category;
import jakarta.persistence.*;

@Entity
@Table(name = "category_attributes")
public class CategoryAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AttributeType attributeType;

    @Column(nullable = false)
    private boolean required;

    public CategoryAttribute(Category category, String name, AttributeType attributeType, boolean required) {
        this.category = category;
        this.name = name;
        this.attributeType = attributeType;
        this.required = required;
    }

    protected CategoryAttribute() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public AttributeType getAttributeType() {
        return attributeType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setAttributeType(AttributeType attributeType) {
        this.attributeType = attributeType;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }
}
