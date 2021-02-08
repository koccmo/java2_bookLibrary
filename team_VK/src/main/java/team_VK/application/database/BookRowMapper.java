package team_VK.application.database;

import org.springframework.jdbc.core.RowMapper;
import team_VK.application.core.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;


public class BookRowMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setID (rs.getLong("id"));
        book.setBookTitle (rs.getString("title"));
        book.setBookAuthor(rs.getString("author"));
        return book;
    }

}
