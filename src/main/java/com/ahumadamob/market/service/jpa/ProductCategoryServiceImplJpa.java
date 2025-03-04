package com.ahumadamob.market.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ahumadamob.market.dto.ProductCategoryPathDTO;
import com.ahumadamob.market.entity.ProductCategory;
import com.ahumadamob.market.repository.ProductCategoryRepository;
import com.ahumadamob.market.service.IProductCategoryService;

@Service
public class ProductCategoryServiceImplJpa implements IProductCategoryService {
	
	@Autowired
	private ProductCategoryRepository r;

	@Override
	public Page<ProductCategory> getAll(Pageable pageable) {
		return r.findAll(pageable);
	}

	@Override
	public ProductCategory getById(Long id) {
		return (id==null)?null:r.findById(id).orElse(null);
	}

	@Override
	public boolean existsById(Long id) {
		return (id == null)?false:r.existsById(id);
	}

	@Override
	public ProductCategory save(ProductCategory productCategory) {
		return r.save(productCategory);
	}

	@Override
	public void deleteById(Long id) {
		r.deleteById(id);
	}

	@Override
	public List<ProductCategoryPathDTO> getPaths() {
		return r.findAllCategoryPaths();
	}

}
