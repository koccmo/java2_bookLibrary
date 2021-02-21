package team_VK.application.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import team_VK.application.core.domain.Book;

import java.util.List;

//@Component
public class JdbcBookRepositoryImpl implements BookRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addBook(Book book) {

        jdbcTemplate.update(
                "INSERT INTO books (title, author, bookingPeriod) "
                        + "VALUES (?, ?, ?)",
                book.getBookTitle(), book.getBookAuthor(), book.getBookingDurationPermitted()
        );
    }

    @Override
    public boolean deleteBook(Book book) {
        String sql = "DELETE FROM books WHERE id = ?";
        Object[] args = new Object[] {book.getID()};
        return jdbcTemplate.update(sql, args) == 1;
    }

    @Override
    public List<Book> getListBooks() {

        String sql = "SELECT * FROM books";
        return jdbcTemplate.query(sql, new BookRowMapper());

    }



}
