package bookLibrary.core.dataBase;

import bookLibrary.core.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcDatabaseImpl implements DataBase{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void addBook(Book book) {
        jdbcTemplate.update("INSERT INTO books (author, title)"
                                    + "VALUES (?, ?)",
                                    book.getAuthor(), book.getTitle());
    }

    @Override
    public void deleteBook(Long id) {
        jdbcTemplate.update("DELETE FROM books WHERE id = ?", id);
    }

    @Override
    public void finishWork() {
        System.exit(0);
    }

    @Override
    public List<String> getAllBooksTitle() {
        String query = "SELECT title FROM books";
        List<String> names = jdbcTemplate.queryForList(query,String.class);
        return names;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        String sql = "SELECT * FROM books WHERE author = ?";
        Object[] args = new Object[] {author};
        return jdbcTemplate.query(sql, args, new BookRowMapper());

    }

    @Override
    public List<Book> findByTitle(String title) {
        String sql = "SELECT * FROM books WHERE title = ?";
        Object[] args = new Object[] {title};
        return jdbcTemplate.query(sql, args, new BookRowMapper());
    }
    @Override
    public List<Book> findByAuthorAndTitle(String author, String title) {
        String sql = "SELECT * FROM books WHERE author = ? AND title = ?";
        Object[] args = new Object[] {author, title};
        return jdbcTemplate.query(sql, args, new BookRowMapper());
    }

    @Override
    public Long getBookId(String author, String title) {
        String sql = "SELECT id FROM books WHERE author = ? AND title = ?";
        Long result = jdbcTemplate.queryForObject(sql, Long.class, author, title);
        return result;
    }

    @Override
    public boolean hasBookInLibrary(Book book) {
        String sql = "SELECT COUNT(*) FROM books WHERE author = ? AND title = ?";
        Integer result = jdbcTemplate.queryForObject(sql, Integer.class, book.getAuthor(), book.getTitle());
        return result !=0 && result > 0;
    }

    @Override
    public boolean hasBookInLibraryCheckById(Long id) {
        Integer result = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM books WHERE id = ?", Integer.class, id);
        return result != 0 && result > 0;
    }
}
