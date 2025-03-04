package com.ahumadamob.market.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class ProductCategory extends BaseEntity {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no debe superar 100 caracteres")
    private String name;

    @ManyToOne(optional = true)
    @JoinColumn(name = "parent_category_id")
    private ProductCategory parent;

    @PrePersist
    @PreUpdate
    private void validateNoCycle() {
        checkCycle(this, new HashSet<>());
    }

    private void checkCycle(ProductCategory category, Set<ProductCategory> visited) {
        if (category == null) {
            return;
        }
        if (visited.contains(category)) {
            throw new IllegalStateException("Se detectó una referencia circular en la jerarquía de categorías");
        }
        visited.add(category);
        checkCycle(category.getParent(), visited);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ProductCategory getParent() {
        return parent;
    }
    public void setParent(ProductCategory parent) {
        this.parent = parent;
    }
}
