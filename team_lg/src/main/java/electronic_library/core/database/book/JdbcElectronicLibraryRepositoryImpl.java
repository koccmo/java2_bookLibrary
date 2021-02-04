package electronic_library.core.database.book;

import electronic_library.core.database.book.BookRowMapper;
import electronic_library.core.database.book.ElectronicLibraryRepository;
import electronic_library.core.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Profile("jdbc")
public class JdbcElectronicLibraryRepositoryImpl implements ElectronicLibraryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void saveBook(Book book) {
        jdbcTemplate.update("INSERT INTO books (bookTitle, bookAuthor, bookPrice, yearOfBookIssue) VALUES (?, ?, ?, ?)",
                book.getBookTitle(), book.getBookAuthor(), book.getBookPrice(), book.getYearOfBookIssue()
        );
    }

    @Override
    public boolean deleteBook(Book book) {
        return jdbcTemplate.update("DELETE FROM books WHERE bookTitle = ? AND bookAuthor = ?",
                new Object[]{book.getBookTitle(), book.getBookAuthor()}) > 0;
    }

    @Override
    public boolean deleteBookById(Long id) {
        return jdbcTemplate.update("DELETE FROM books WHERE id = ?", new Object[]{id}) == 1;
    }

    @Override
    public boolean deleteBookByTitle(String bookTitle) {
        return jdbcTemplate.update("DELETE FROM books WHERE bookTitle = ?", new Object[]{bookTitle}) > 0;
    }

    @Override
    public boolean deleteBookByAuthor(String bookAuthor) {
        return jdbcTemplate.update("DELETE FROM books WHERE bookAuthor = ?", new Object[]{bookAuthor}) > 0;
    }

    @Override
    public Optional<Book> findBookById(Long id) {
        try {
            Book book = jdbcTemplate.queryForObject("SELECT * FROM books WHERE id = ?", new Object[]{id}, new BookRowMapper());
            return Optional.ofNullable(book);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Book> findBookByTitle(String bookTitle) {
        return jdbcTemplate.query("SELECT * FROM books WHERE bookTitle LIKE ?", new Object[]{bookTitle}, new BookRowMapper());
    }

    @Override
    public List<Book> findBookByAuthor(String bookAuthor) {
        return jdbcTemplate.query("SELECT * FROM books WHERE bookAuthor LIKE ?", new Object[]{bookAuthor}, new BookRowMapper());
    }

    @Override
    public List<Book> findByTitleAndAuthor(String bookTitle, String bookAuthor) {
        return jdbcTemplate.query("SELECT * FROM books WHERE bookTitle LIKE ? AND bookAuthor LIKE ?", new Object[]{bookTitle, bookAuthor}, new BookRowMapper());
    }

    @Override
    public List<Book> getElectronicLibrary() {
        return jdbcTemplate.query("SELECT * FROM books", new BookRowMapper());
    }
}
