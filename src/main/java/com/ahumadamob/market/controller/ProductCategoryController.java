package com.ahumadamob.market.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahumadamob.market.dto.ProductCategoryPathDTO;
import com.ahumadamob.market.dto.ResponseDTO;
import com.ahumadamob.market.entity.ProductCategory;
import com.ahumadamob.market.service.IProductCategoryService;
import com.ahumadamob.market.util.BuildResponse;

@RestController
@RequestMapping(path="/api/product-category")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductCategoryController {

	@Autowired
	private IProductCategoryService productCategoryService;
	
	@GetMapping
	public ResponseEntity<ResponseDTO<Page<ProductCategory>>> getProductCategories(Pageable pageable) {
		Page<ProductCategory> productCategoriesPage = productCategoryService.getAll(pageable);
		return	(productCategoriesPage.isEmpty()) ? BuildResponse.noContent():
			BuildResponse.success(productCategoriesPage);
	}
	
	@GetMapping("/path")
	public ResponseEntity<ResponseDTO<List<ProductCategoryPathDTO>>> getProductCategoriesPath() {
		List<ProductCategoryPathDTO> productCategories = productCategoryService.getPaths();
		return	(productCategories.isEmpty()) ? BuildResponse.noContent():
			BuildResponse.success(productCategories);
	}	
	
    /*
	private String buildPath(ProductCategory category) {
        List<String> names = new ArrayList<>();
        while (category != null) {
            names.add(0, category.getName());
            category = category.getParent();
        }
        return String.join(" > ", names);
    }*/	
	
	@GetMapping("{id}")
	public ResponseEntity<ResponseDTO<ProductCategory>> getProductCategoryById(@PathVariable("id") Long id){
		return	productCategoryService.existsById(id)? BuildResponse.success(productCategoryService.getById(id)):
				BuildResponse.notFound("No se encontró la categoría con id {0}", id);
	}	
	
	@PostMapping
	public ResponseEntity<ResponseDTO<ProductCategory>> createProductCategory(@RequestBody ProductCategory productCategory){
		productCategory.setId(null);
		return BuildResponse.created(productCategoryService.save(productCategory), "Categoría creada correctamente");
	}
	
	@PutMapping
	public ResponseEntity<ResponseDTO<ProductCategory>> updateProductCategory(@RequestBody ProductCategory productCategory){
		return	productCategoryService.existsById(productCategory.getId())? BuildResponse.success(
				productCategoryService.save(productCategory), "Categoría creada correctamente"):
				BuildResponse.badRequest("No existe la categoría con id {0}.", productCategory.getId());
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<ResponseDTO<Object>> deleteProductCategory(@PathVariable("id") Long id){
		if(	productCategoryService.existsById(id)) {
			productCategoryService.deleteById(id);
			return BuildResponse.success("Categoría eliminada correctamente.");
		}else {
			return BuildResponse.badRequest("No existe el ajuste con id {0}.", id);
		}				
	}
}
