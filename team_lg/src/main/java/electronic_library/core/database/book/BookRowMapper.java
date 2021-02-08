package electronic_library.core.database.book;

import electronic_library.core.domain.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getLong("id"));
        book.setBookTitle(rs.getString("bookTitle"));
        book.setBookAuthor(rs.getString("bookAuthor"));
        book.setBookPrice(rs.getBigDecimal("bookPrice"));
        book.setYearOfBookIssue(rs.getInt("yearOfBookIssue"));
        return book;
    }
}
