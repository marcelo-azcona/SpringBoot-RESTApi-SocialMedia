package com.azcona.socialmediaApp.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();
	private static int userCount = 3;

	static {
		users.add(new User(++userCount, "Marcelo", LocalDate.now().minusYears(30)));
		users.add(new User(++userCount, "Eliana", LocalDate.now().minusYears(29)));
		users.add(new User(++userCount, "Titina", LocalDate.now().minusYears(60)));
	}

	public List<User> findAll() {
		return users;
	}

	public User findOne(int id) {
		return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
	}

	public User saveUser(User user) {
		user.setId(++userCount);
		users.add(user);

		return user;
	}

	public void deleteById(int id) {
		users.removeIf(user -> user.getId().equals(id));
	}
}
