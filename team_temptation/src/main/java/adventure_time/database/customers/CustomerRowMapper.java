package adventure_time.database.customers;

import adventure_time.core.domain.Customers;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customers> {
    @Override
    public Customers mapRow(ResultSet rs, int rowNum) throws SQLException {

        Customers customer = new Customers();
        customer.setCustomerID(rs.getLong("id"));
        customer.setCustomerName(rs.getString("name"));
        customer.setCustomerEmail(rs.getString("email"));
        customer.setCustomerPhone(rs.getString("phone"));
        customer.setCustomerPassword(rs.getString("password"));
        customer.setActivity(rs.getBoolean("activity"));
        return customer;
    }
}
