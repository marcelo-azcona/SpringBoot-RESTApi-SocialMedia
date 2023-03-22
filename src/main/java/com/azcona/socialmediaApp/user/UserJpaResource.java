package com.azcona.socialmediaApp.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.azcona.socialmediaApp.jpa.PostRepository;
import com.azcona.socialmediaApp.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {

	private UserRepository userRepository;
	private PostRepository postRepository;

	public UserJpaResource(UserRepository theUserRepository, PostRepository thePostRepository) {
		this.userRepository = theUserRepository;
		this.postRepository = thePostRepository;
	}

	// GET
	@GetMapping("/jpa/users")
	public List<User> retriveAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/jpa/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);

		if (user.isEmpty()) {
			throw new UserNotFoundException("id: " + id);
		}

		return user.get();
	}

	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrievePostForUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);

		if (user.isEmpty()) {
			throw new UserNotFoundException("id: " + id);
		}

		return user.get().getPosts();
	}

	// POST
	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);

		// To retrieve the URI of the POST request
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
		Optional<User> user = userRepository.findById(id);

		if (user.isEmpty()) {
			throw new UserNotFoundException("id: " + id);
		}

		post.setUser(user.get());

		Post savedPost = postRepository.save(post);

		// To retrieve the URI of the POST request
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	// DELETE
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}

	// FALTA METODOS PARA BORRAR UN POST O TODOS LOS POST
}
