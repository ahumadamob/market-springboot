package com.ahumadamob.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahumadamob.market.entity.StandardProduct;

public interface StandardProductRepository extends JpaRepository<StandardProduct, Long> {

}
