package com.ahumadamob.market.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ahumadamob.market.entity.AdjustmentConfig;

public interface IAdjustmentConfigService {
	public Page<AdjustmentConfig> getAll(Pageable pageable);
	public AdjustmentConfig getById(Long id);
	public boolean existsById(Long id);
	public AdjustmentConfig save(AdjustmentConfig adjustmentConfig);
	public void deleteById(Long id);
}
