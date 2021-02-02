package dental_clinic.core.database.manipulation;

import dental_clinic.core.domain.Manipulation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
/*
public class ManipulationRowMapper implements RowMapper<Manipulation> {

    @Override
    public Manipulation mapRow(ResultSet rs, int rowNum) throws SQLException {
        Manipulation manipulation = new Manipulation();
        manipulation.setId(rs.getLong("id"));
        manipulation.setManipulation_type(rs.getString("service_type"));
        manipulation.setPrice(rs.getInt("price"));
        manipulation.setActive(rs.getBoolean("isActive"));
        return manipulation;
    }
}*/
