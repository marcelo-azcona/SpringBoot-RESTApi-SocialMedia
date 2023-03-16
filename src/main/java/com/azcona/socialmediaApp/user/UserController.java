package com.azcona.socialmediaApp.user;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	private UserDaoService userService;

	public UserController(UserDaoService theService) {
		this.userService = theService;
	}

	@GetMapping("/users")
	public List<User> retriveAllUsers() {
		return userService.findAll();
	}

	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		return userService.findOne(id);
	}
}
