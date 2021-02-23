package book_library.core.database.Mapper.Book;

import book_library.core.domain.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewBookIdMapper implements RowMapper<Long> {

    @Override
    public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("MAX(id)");
        return id;
    }
}
