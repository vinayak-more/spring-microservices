package com.vinayak.restfulservice.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

}
