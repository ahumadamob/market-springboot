package com.ahumadamob.market.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.ahumadamob.market.dto.ResponseDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;


public class BuildResponse {
    
	private BuildResponse() {}
    
	public static<T> ResponseEntity<ResponseDTO<T>> success(T data) {        
		ResponseDTO<T> res = new ResponseDTO<>(HttpStatus.OK.value(), data);
		return ResponseEntity.status(HttpStatus.OK).body(res);
    }
	
	public static<T> ResponseEntity<ResponseDTO<T>> success(String message) {        
		ResponseDTO<T> res = new ResponseDTO<>(HttpStatus.OK.value());
		res.addInfo(null, message);
		return ResponseEntity.status(HttpStatus.OK).body(res);		
    }	
	
	public static<T> ResponseEntity<ResponseDTO<T>> success(T data, String message) {        
		ResponseDTO<T> res = new ResponseDTO<>(HttpStatus.OK.value(), data);
		res.addInfo(null, message);
		return ResponseEntity.status(HttpStatus.OK).body(res);
    }
	
	public static<T> ResponseEntity<T> noContent() {
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}	
	    
	public static<T> ResponseEntity<ResponseDTO<T>> created(T data, String message) {        
		ResponseDTO<T> res = new ResponseDTO<>(HttpStatus.CREATED.value(), data);
		res.addInfo(null, message);
		return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
	
	public static<T> ResponseEntity<ResponseDTO<T>> created(T data) {        
		ResponseDTO<T> res = new ResponseDTO<>(HttpStatus.CREATED.value(), data);
		res.addInfo(null, "Registro creado correctamente");
		return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
	
	public static<T> ResponseEntity<ResponseDTO<T>> modified(T data) {        
		ResponseDTO<T> res = new ResponseDTO<>(HttpStatus.OK.value(), data);
		res.addInfo(null, "Registro modificado correctamente");
		return ResponseEntity.status(HttpStatus.OK).body(res);
    }	

	public static<T> ResponseEntity<ResponseDTO<T>> notFound(String message, Long id) {
		ResponseDTO<T> res = new ResponseDTO<>(HttpStatus.NOT_FOUND.value());
		res.addError("id", injectId(message, id) );
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
	}
	
	public static<T> ResponseEntity<ResponseDTO<T>> notFound(String message) {
		ResponseDTO<T> res = new ResponseDTO<>(HttpStatus.NOT_FOUND.value());
		res.addError(null, message);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
	}

	public static<T> ResponseEntity<ResponseDTO<T>> badRequest(String message, Long id) {
		ResponseDTO<T> res = new ResponseDTO<>(HttpStatus.BAD_REQUEST.value());
		res.addError("id", injectId(message, id) );
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
	}
	
	public static<T> ResponseEntity<ResponseDTO<T>> badRequest(String field, String message) {
		ResponseDTO<T> res = new ResponseDTO<>(HttpStatus.BAD_REQUEST.value());
		res.addError(field, message);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
	}	
	
	public static<T> ResponseEntity<ResponseDTO<T>> delete(String entity, Long id) {
		String message = "Se ha eliminado el registro de " + entity + " con id = " + id.toString();
		ResponseDTO<T> res = new ResponseDTO<>(HttpStatus.OK.value());
		res.addInfo(null, message);
		return ResponseEntity.status(HttpStatus.OK).body(res);	
	}
	
    public static <T> ResponseEntity<ResponseDTO<T>> handleConstraintException(ConstraintViolationException ex) {
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getMessage());
        }
        ResponseDTO<T> res = new ResponseDTO<T>(HttpStatus.BAD_REQUEST.value(), ex);
        return ResponseEntity.badRequest().body(res);
    }
	
	private static String injectId(String message, Long id) {
		return id==null?message.replace("{0}", ""):message.replace("{0}", id.toString());
	}


}
