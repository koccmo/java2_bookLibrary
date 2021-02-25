package lv.javaguru.app.database.repository;

import lv.javaguru.app.core.domain.User;

import java.util.List;

public interface UserRepository {

	Long addUser (User user);

	User getUserByUsername (String username);

	User getUserByNameAndSurname (String name, String surname);

	boolean updateUserNameByUserId (Long id, String name);

	boolean updateUserSurnameById (Long id, String surname);

	User getUserById (Long id);

	List<User> getAllUsers ();

	boolean removeUser (Long id);
}
