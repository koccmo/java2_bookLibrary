package electronic_library.core.database.reader;

import electronic_library.core.domain.Reader;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReaderRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Reader reader = new Reader();
        reader.setId(rs.getLong("id"));
        reader.setReaderFirstName(rs.getString("firstName"));
        reader.setReaderLastName(rs.getString("lastName"));
        reader.setReaderPersonalCode(rs.getString("personalCode"));
        reader.setReaderPhoneNumber(rs.getString("phoneNumber"));
        reader.setReaderEmail(rs.getString("email"));
        reader.setReaderAddress(rs.getString("address"));
        return reader;
    }
}
