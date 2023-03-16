package com.azcona.socialmediaApp.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();

	static {
		users.add(new User(1, "Marcelo", LocalDate.now().minusYears(30)));
		users.add(new User(2, "Eliana", LocalDate.now().minusYears(29)));
		users.add(new User(3, "Titina", LocalDate.now().minusYears(60)));
	}

	public List<User> findAll() {
		return users;
	}

	public User findOne(int id) {

		return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
	}
}
