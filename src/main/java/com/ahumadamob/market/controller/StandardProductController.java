package com.ahumadamob.market.controller;

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

import com.ahumadamob.market.dto.ResponseDTO;
import com.ahumadamob.market.entity.StandardProduct;
import com.ahumadamob.market.service.IStandardProductService;
import com.ahumadamob.market.util.BuildResponse;

@RestController
@RequestMapping(path="/api/product")
@CrossOrigin(origins = "http://localhost:4200")
public class StandardProductController {
	
	@Autowired
	private IStandardProductService standardProductService;
	
	@GetMapping
	public ResponseEntity<ResponseDTO<Page<StandardProduct>>> getProducts(Pageable pageable) {
		Page<StandardProduct> productsPage = standardProductService.getAll(pageable);
		return	(productsPage.isEmpty()) ? BuildResponse.noContent():
			BuildResponse.success(productsPage);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ResponseDTO<StandardProduct>> getProductById(@PathVariable("id") Long id){
		return	standardProductService.existsById(id)? BuildResponse.success(standardProductService.getById(id)):
				BuildResponse.notFound("No se encontró el producto con id {0}", id);
	}	
	
	@PostMapping
	public ResponseEntity<ResponseDTO<StandardProduct>> createProduct(@RequestBody StandardProduct product){
		product.setId(null);
		return BuildResponse.created(standardProductService.save(product), "Producto creado correctamente");
	}
	
	@PutMapping
	public ResponseEntity<ResponseDTO<StandardProduct>> updateProduct(@RequestBody StandardProduct product){
		return	standardProductService.existsById(product.getId())? BuildResponse.success(
				standardProductService.save(product), "Producto creado correctamente"):
				BuildResponse.badRequest("No existe el producto con id {0}.", product.getId());
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<ResponseDTO<Object>> deleteProduct(@PathVariable("id") Long id){
		if(	standardProductService.existsById(id)) {
			standardProductService.deleteById(id);
			return BuildResponse.success("Producto eliminado correctamente");
		}else {
			return BuildResponse.badRequest("No existe el producto con id {0}.", id);
		}				
	}
}
