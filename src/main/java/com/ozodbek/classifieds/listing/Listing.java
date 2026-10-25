package com.ozodbek.classifieds.listing;

import com.ozodbek.classifieds.category.Category;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import tools.jackson.databind.JsonNode;

import java.math.BigDecimal;

@Entity
@Table(name = "listings")
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(nullable = false)
    private JsonNode attributes;

    public Listing(String title, BigDecimal price, Category category, JsonNode attributes) {
        this.title = title;
        this.price = price;
        this.category = category;
        this.attributes = attributes;
    }

    protected Listing() {}

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public JsonNode getAttributes() {
        return attributes;
    }

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changePrice(BigDecimal price) {
        this.price = price;
    }

    public void changeCategory(Category category) {
        this.category = category;
    }

    public void changeAttributes(JsonNode attributes) {
        this.attributes = attributes;
    }
}
