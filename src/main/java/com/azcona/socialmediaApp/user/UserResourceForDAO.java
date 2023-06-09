package com.azcona.socialmediaApp.user;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserResourceForDAO {

	private UserDaoService userService;

	public UserResourceForDAO(UserDaoService theService) {
		this.userService = theService;
	}

	// GET
	@GetMapping("/users")
	public List<User> retriveAllUsers() {
		return userService.findAll();
	}

	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		User user = userService.findOne(id);

		if (user == null) {
			throw new UserNotFoundException("id: " + id);
		}

		return user;
	}

	// POST
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userService.saveUser(user);

		// To retrieve the URI of the POST request
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	// DELETE
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userService.deleteById(id);
	}

}
