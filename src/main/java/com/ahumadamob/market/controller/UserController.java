package com.ahumadamob.market.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	public ResponseEntity<ResponseDTO<List<User>>> getUsers() {
		List<User> users = userService.getAll();
		return	(users.isEmpty()) ? BuildResponse.notFound("No se encontraron usuarios."):
			BuildResponse.success(users);
	}
	
	@PostMapping
	public ResponseEntity<ResponseDTO<User>> createUser(@RequestBody User user){
		user.setId(null);
		return BuildResponse.created(userService.save(user), "Usuario creado correctamente");
	}
}
