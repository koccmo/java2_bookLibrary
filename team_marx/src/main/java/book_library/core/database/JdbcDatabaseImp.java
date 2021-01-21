package book_library.core.database;

import book_library.core.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcDatabaseImp implements Database{

    @Autowired private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Book book) {
        jdbcTemplate.update(
                "INSERT INTO books (title, author)"
                + "VALUES (?, ?)",
                book.getTitle(), book.getAuthor()
        );
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public boolean hasTheSameBookInDatabase(Book bookToCompare) {
        return false;
    }

    @Override
    public List<Book> getAllBooks() {
        return null;
    }

    @Override
    public List<Book> findByTitle(String title) {
        return null;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return null;
    }

    @Override
    public List<Book> findByTitleAndAuthor(String title, String author) {
        return null;
    }
}
