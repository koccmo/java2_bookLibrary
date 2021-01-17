package java2.application_target_list.core.database.user;


import java2.application_target_list.core.domain.User;

import java.util.List;

public interface UserDatabase {
    void addUser(User user);
    boolean deleteUser(Long userId);
    boolean changeUserFirstName(Long userId, String newName);
    boolean changeUserLastName(Long userId, String newLastName);
    List<User> getUsersList();
    List<User> findUserByFirstName(String userFirstName);
    List<User> findUserByLastName(String userLastName);
    boolean isIdInUserList(Long userId);

}
