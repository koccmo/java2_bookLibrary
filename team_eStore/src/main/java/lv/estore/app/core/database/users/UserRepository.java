package lv.estore.app.core.database.users;

import lv.estore.app.core.domain.User;

import java.util.List;

public interface UserRepository {
    Long addUser(final User user);
    List<User> getAllUsers();
    boolean removeUserByEmail(final String email);
    boolean removeUserById(final Long id);
    User findUserById(final Long id);
    List<User> findUsersByLastName(final String name);
    boolean updateUserById(final Long id, final String firstName, final String lastName, final String email);
}
