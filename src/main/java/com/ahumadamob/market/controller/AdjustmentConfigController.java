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
import com.ahumadamob.market.entity.AdjustmentConfig;
import com.ahumadamob.market.service.IAdjustmentConfigService;
import com.ahumadamob.market.util.BuildResponse;

@RestController
@RequestMapping(path="/api/adjustment-config")
@CrossOrigin(origins = "http://localhost:4200") // Cambia la URL según tu aplicación Angular
public class AdjustmentConfigController {
	
	@Autowired
	private IAdjustmentConfigService adjustmentConfigService;
	
	@GetMapping
	public ResponseEntity<ResponseDTO<Page<AdjustmentConfig>>> getAdjustmentConfigs(Pageable pageable) {
		Page<AdjustmentConfig> adjustmentConfigsPage = adjustmentConfigService.getAll(pageable);
		return	(adjustmentConfigsPage.isEmpty()) ? BuildResponse.noContent():
			BuildResponse.success(adjustmentConfigsPage);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ResponseDTO<AdjustmentConfig>> getAdjustmentConfigById(@PathVariable("id") Long id){
		return	adjustmentConfigService.existsById(id)? BuildResponse.success(adjustmentConfigService.getById(id)):
				BuildResponse.notFound("No se encontró el ajuste con id {0}", id);
	}	
	
	@PostMapping
	public ResponseEntity<ResponseDTO<AdjustmentConfig>> createAdjustmentType(@RequestBody AdjustmentConfig adjustmentConfig){
		adjustmentConfig.setId(null);
		return BuildResponse.created(adjustmentConfigService.save(adjustmentConfig), "Ajuste creado correctamente");
	}
	
	@PutMapping
	public ResponseEntity<ResponseDTO<AdjustmentConfig>> updateAdjustmentConfig(@RequestBody AdjustmentConfig adjustmentConfig){
		return	adjustmentConfigService.existsById(adjustmentConfig.getId())? BuildResponse.success(
				adjustmentConfigService.save(adjustmentConfig), "Ajuste modificado correctamente"):
				BuildResponse.badRequest("No existe el usuario con id {0}.", adjustmentConfig.getId());
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<ResponseDTO<Object>> deleteAdjustmentConfig(@PathVariable("id") Long id){
		if(adjustmentConfigService.existsById(id)) {
			adjustmentConfigService.deleteById(id);
			return BuildResponse.success("Ajuste eliminado correctamente.");
		}else {
			return BuildResponse.badRequest("No existe el ajuste con id {0}.", id);
		}				
	}	

}
