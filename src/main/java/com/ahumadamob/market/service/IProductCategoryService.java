package com.ahumadamob.market.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ahumadamob.market.dto.ProductCategoryPathDTO;
import com.ahumadamob.market.entity.ProductCategory;

public interface IProductCategoryService {
	public Page<ProductCategory> getAll(Pageable pageable);
	public ProductCategory getById(Long id);
	public boolean existsById(Long id);
	public ProductCategory save(ProductCategory productCategory);
	public void deleteById(Long id);
	public List<ProductCategoryPathDTO> getPaths();
}
