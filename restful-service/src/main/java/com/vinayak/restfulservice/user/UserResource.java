package com.vinayak.restfulservice.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vinayak.restfulservice.exception.UserNotFoundException;

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
		User user = service.findOne(id);
		if (user == null) {
			throw new UserNotFoundException(String.format("User not found for id %d", id));
		}
		return user;
	}

	@PostMapping("/users")
	public ResponseEntity<Object> save(@RequestBody User user) {
		User savedUser = service.save(user);
		URI userURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(userURI).build();

	}

	@DeleteMapping("/users/{id}")
	public void delete(@PathVariable int id) {
		User deletedUser = service.delete(id);
		if (deletedUser == null) {
			throw new UserNotFoundException(String.format("User not found for id %d", id));
		}
	}

}
