package com.ahumadamob.market.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ahumadamob.market.entity.StandardProduct;
import com.ahumadamob.market.repository.StandardProductRepository;
import com.ahumadamob.market.service.IStandardProductService;

@Service
public class StandardProductServiceImplJpa implements IStandardProductService {
	
	@Autowired
	private StandardProductRepository r;

	@Override
	public Page<StandardProduct> getAll(Pageable pageable) {
		return r.findAll(pageable);
	}

	@Override
	public StandardProduct getById(Long id) {
		return (id==null)?null:r.findById(id).orElse(null);
	}

	@Override
	public boolean existsById(Long id) {
		return (id == null)?false:r.existsById(id);
	}

	@Override
	public StandardProduct save(StandardProduct product) {
		return r.save(product);
	}

	@Override
	public void deleteById(Long id) {
		r.deleteById(id);
		
	}

}
