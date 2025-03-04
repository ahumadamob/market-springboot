package com.ahumadamob.market.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ahumadamob.market.entity.StandardProduct;

public interface IStandardProductService {
	public Page<StandardProduct> getAll(Pageable pageable);
	public StandardProduct getById(Long id);
	public boolean existsById(Long id);
	public StandardProduct save(StandardProduct product);
	public void deleteById(Long id);
}
