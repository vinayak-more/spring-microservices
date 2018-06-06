package com.vinayak.restfulservice.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	@Autowired
	private UserService service;

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return service.findAll();
	}

	@GetMapping("/users/{id}")
	public User getUser(@PathVariable int id) {
		return service.findOne(id);
	}

	@PostMapping("/users")
	public ResponseEntity<Object> save(@RequestBody User user) {
		User userId = service.save(user);
		URI userURI = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}")
				.queryParam("id", userId.getId()).build().toUri();
		ResponseEntity<Object> responseEntity = new ResponseEntity<>(HttpStatus.CREATED);
		responseEntity.getHeaders().setLocation(userURI);
		return responseEntity;
	}

}
