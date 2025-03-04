package com.ahumadamob.market.dto;

public class ProductCategoryPathDTO {
    private Long id;
    private String value;

    public ProductCategoryPathDTO(Long id, String value) {
        this.id = id;
        this.value = value;
    }

	public Long getId() {
		return id;
	}

	public String getValue() {
		return value;
	}


}

