package lv.javaguru.app.database;

import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.dependency_injection.DIComponent;

import java.util.*;

@DIComponent
public class UserDatabase {

	private final Map<Long, User> userDatabase = new HashMap<>();

	private static Long nextId = 1L;
	private User currentUser;


	public User getCurrentUser () {
		return currentUser;
	}

	public void setCurrentUser (User currentUser) {
		this.currentUser = currentUser;
	}

	public void addUser (User user) {
		if (!userDatabase.containsKey(user.getId())) {
			user.setId(nextId++);
			userDatabase.put(user.getId(), user);
		}
	}

	public Optional<User> getUser (User user) {
		return userDatabase.values().stream()
				.filter(u -> u.equals(user))
				.findFirst();
	}

	public List<User> getAllUsers () {
		return new ArrayList<>(userDatabase.values());
	}

	public User getUserById (Long id) {
		return userDatabase.get(id);
	}

	public boolean containsUser (Long id) {
		return userDatabase.containsKey(id);
	}

	public boolean deleteUserById (Long id) {
		User user = userDatabase.get(id);
		return userDatabase.remove(id, user);
	}
}
