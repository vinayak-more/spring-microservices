package com.vinayak.restfulservice.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	private static List<User> users = new ArrayList<>();

	static {
		users.add(new User(1, "Sachin", new Date()));
		users.add(new User(2, "Virat", new Date()));
		users.add(new User(3, "Ravichandra", new Date()));
	}

	public List<User> findAll() {
		return users;
	}

	public User findOne(int id) {
		Optional<User> userOptional = users.stream().peek(System.out::println)
				.filter(user -> user.getId().intValue() == id).findFirst();
		if (userOptional.isPresent()) {
			return userOptional.get();
		} else {
			return null;
		}
	}
}
