package lv.javaguru.app.database.rowmappers;

import lv.javaguru.app.core.domain.PersonType;
import lv.javaguru.app.core.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow (ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setId(Long.parseLong(rs.getString("users.id")));
		user.setUsername(rs.getString("users.username"));
		user.setPassword(rs.getString("users.password"));
		//user.setPersonType(rs.getString("users.person_type").equals("CLIENT") ?
	//			PersonType.CLIENT : PersonType.ADMIN);

		return user;
	}
}
