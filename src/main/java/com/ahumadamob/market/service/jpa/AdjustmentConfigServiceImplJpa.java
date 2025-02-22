package com.ahumadamob.market.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ahumadamob.market.entity.AdjustmentConfig;
import com.ahumadamob.market.repository.AdjustmentConfigRepository;
import com.ahumadamob.market.service.IAdjustmentConfigService;

@Service
public class AdjustmentConfigServiceImplJpa implements IAdjustmentConfigService {
	
	@Autowired
	private AdjustmentConfigRepository r;

	@Override
	public Page<AdjustmentConfig> getAll(Pageable pageable) {
		return r.findAll(pageable);
	}

	@Override
	public AdjustmentConfig getById(Long id) {
		return (id==null)?null:r.findById(id).orElse(null);
	}

	@Override
	public boolean existsById(Long id) {
		return (id == null)?false:r.existsById(id);
	}

	@Override
	public AdjustmentConfig save(AdjustmentConfig adjustmentConfig) {
		return r.save(adjustmentConfig);

	}

	@Override
	public void deleteById(Long id) {
		r.deleteById(id);		
	}

}
