package java2.application_target_list.core.database.user;


import java2.application_target_list.core.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@Profile("mysql")
public class UserListJDBCImpl implements UserDatabase{

    @Autowired JdbcTemplate jdbcTemplate;

    @Override
    public void addUser(User user) {
        jdbcTemplate.update(
                "INSERT INTO users (user_first_name, user_last_name) " +
                        "VALUES (?, ?)", user.getFirstName(), user.getLastName());
    }

    @Override
    public boolean deleteUser(Long userId) {
        jdbcTemplate.update("DELETE FROM users WHERE id = " + userId);
        return true;
    }

    @Override
    public boolean changeUserFirstName(Long userId, String newName) {
        jdbcTemplate.update("UPDATE users SET user_first_name = ? WHERE id = ?", newName, userId);
        return true;
    }

    @Override
    public boolean changeUserLastName(Long userId, String newLastName) {
        jdbcTemplate.update("UPDATE users SET user_last_name = ? WHERE id = ?", newLastName, userId);
        return true;
    }

    @Override
    public List<User> getUsersList() {
        return jdbcTemplate.query("SELECT * FROM users", new UserListJDBCImpl.UsersMapper());
    }

    @Override
    public List<User> findUserByFirstName(String userFirstName) {
        return jdbcTemplate.query("SELECT * FROM users WHERE user_first_name = ?", new Object[]{userFirstName} , new UserListJDBCImpl.UsersMapper());
    }

    @Override
    public List<User> findUserByLastName(String userLastName) {
        return jdbcTemplate.query("SELECT * FROM users WHERE user_last_name = ?", new Object[]{userLastName} , new UserListJDBCImpl.UsersMapper());
    }

    @Override
    public boolean isIdInUserList(Long userId) {
        List<User> users = getUsersList();

        for (User tempUser : users) {
            if (tempUser.getId().equals(userId)) {
                return true;
            }
        }
        return false;
    }

    private class UsersMapper implements RowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User(rs.getString("user_first_name"),
                    rs.getString("user_last_name"));
            user.setId(rs.getLong("id"));
            return user;
        }
    }
}
