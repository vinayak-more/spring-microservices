package com.vinayak.restfulservice.user;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vinayak.restfulservice.exception.UserNotFoundException;

@RestController()
@RequestMapping("/users")
public class UserResource {

	@Autowired
	private UserService service;

	@GetMapping
	public List<Resource<User>> getAllUsers() {
		List<User> allUsers = service.findAll();
		List<Resource<User>> allUserResources = allUsers.stream().map(user -> {
			Resource<User> resource = new Resource<>(user);
			ControllerLinkBuilder linkTo = ControllerLinkBuilder
					.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getUser(user.getId()));
			resource.add(linkTo.withRel("self"));
			return resource;
		}).collect(Collectors.toList());
		return allUserResources;
	}

	@GetMapping("/{id}")
	public Resource<User> getUser(@PathVariable int id) {
		User user = service.findOne(id);
		if (user == null) {
			throw new UserNotFoundException(String.format("User not found for id %d", id));
		}
		Resource<User> resource = new Resource<>(user);
		ControllerLinkBuilder linkTo = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getAllUsers());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}

	@PostMapping()
	public ResponseEntity<Object> save(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		URI userURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(userURI).build();

	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		User deletedUser = service.delete(id);
		if (deletedUser == null) {
			throw new UserNotFoundException(String.format("User not found for id %d", id));
		}
	}

}
