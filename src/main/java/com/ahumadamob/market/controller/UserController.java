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
import com.ahumadamob.market.entity.User;
import com.ahumadamob.market.service.IUserService;
import com.ahumadamob.market.util.BuildResponse;

@RestController
@RequestMapping(path="/api/user")
@CrossOrigin(origins = "http://localhost:4200") // Cambia la URL según tu aplicación Angular
public class UserController {
    @Autowired
	private IUserService userService;
	
	@GetMapping
	public ResponseEntity<ResponseDTO<Page<User>>> getUsers(Pageable pageable) {
		Page<User> usersPage = userService.getAll(pageable);
		return	(usersPage.isEmpty()) ? BuildResponse.notFound("No se encontraron usuarios."):
			BuildResponse.success(usersPage);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ResponseDTO<User>> getUserById(@PathVariable("id") Long id){
		return	userService.existsById(id)? BuildResponse.success(userService.getById(id)):
				BuildResponse.notFound("No se encontró la categoría con id {0}.", id);
	}	
	
	@PostMapping
	public ResponseEntity<ResponseDTO<User>> createUser(@RequestBody User user){
		user.setId(null);
		return BuildResponse.created(userService.save(user), "Usuario creado correctamente");
	}
	
	@PutMapping
	public ResponseEntity<ResponseDTO<User>> updateUser(@RequestBody User user){
		return	userService.existsById(user.getId())? BuildResponse.success(
				userService.save(user), "Usuario modificado correctamente"):
				BuildResponse.badRequest("No existe el usuario con id {0}.", user.getId());
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<ResponseDTO<Object>> deleteUser(@PathVariable("id") Long id){
		if(userService.existsById(id)) {
			userService.deleteById(id);
			return BuildResponse.success("Usuario eliminado correctamente.");
		}else {
			return BuildResponse.badRequest("No existe el usuario con id {0}.", id);
		}				
	}	
}
