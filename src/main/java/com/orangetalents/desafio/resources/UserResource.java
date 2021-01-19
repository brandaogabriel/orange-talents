package com.orangetalents.desafio.resources;

import com.orangetalents.desafio.dtos.UserDTO;
import com.orangetalents.desafio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;

	@PostMapping
	public ResponseEntity<UserDTO> insert(@RequestBody UserDTO userDTO) {
		userDTO = service.insert(userDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{email}")
				.buildAndExpand(userDTO.getEmail()).toUri();
		return ResponseEntity.created(uri).body(userDTO);
	}

	@GetMapping(value = "/{email}")
	public ResponseEntity<UserDTO> findByEmail(@PathVariable String email) {
		UserDTO userDTO = service.findByEmail(email);
		return ResponseEntity.ok().body(userDTO);
	}
}
