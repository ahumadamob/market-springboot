package com.ahumadamob.market.entity;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@MappedSuperclass
public abstract class BaseProduct extends BaseEntity {
	
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no debe superar 100 caracteres")
	private String name;
	 
    private String description;
    
    @NotNull(message = "Precio no puede estar vacío")
    @DecimalMin(value = "0.0", inclusive = false, message = "Precio debe ser positivo")	
    private Double price;
    
    @ManyToOne(optional = true)
    @NotNull(message = "La categoría es obligatoria")
    private ProductCategory productCategory;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}
    
    

}
