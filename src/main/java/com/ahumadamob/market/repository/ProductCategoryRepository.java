package com.ahumadamob.market.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ahumadamob.market.entity.ProductCategory;
import com.ahumadamob.market.dto.ProductCategoryPathDTO;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

	@Query("SELECT new com.ahumadamob.market.dto.ProductCategoryPathDTO(c.id, c.name) FROM ProductCategory c")
    List<ProductCategoryPathDTO> findAllCategoryPaths();

}

