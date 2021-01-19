package electronic_library.core.database;

import electronic_library.core.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Component
@Profile("jdbc")
class JdbcElectronicLibraryImpl implements ElectronicLibrary {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcElectronicLibraryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveBook(Book book) {
        jdbcTemplate.update("INSERT INTO book (bookTitle, bookAuthor, bookPrice, yearOfBookIssue) "
                        + "VALUES (?, ?, ?, ?)",
                book.getBookTitle(), book.getBookAuthor(), book.getBookPrice(), book.getYearOfBookIssue()
        );
    }

    @Override
    public boolean deleteBook(Book book) {
        int answer = jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM book WHERE bookTitle = ? AND bookAuthor = ?");
            statement.setString(1, book.getBookTitle());
            statement.setString(2, book.getBookAuthor());
            return statement;
        });
        return answer > 0;
    }

    @Override
    public boolean deleteBookById(Long id) {
        return jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM book WHERE id = ?");
            statement.setLong(1, id);
            return statement;
        }) == 1;
    }

    @Override
    public boolean deleteBookByTitle(String bookTitle) {
        int answer = jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM book WHERE bookTitle = ?");
            statement.setString(1, bookTitle);
            return statement;
        });
        return answer > 0;
    }

    @Override
    public boolean deleteBookByAuthor(String bookAuthor) {
        int answer = jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM book WHERE bookAuthor = ?");
            statement.setString(1, bookAuthor);
            return statement;
        });
        return answer > 0;
    }

    @Override
    public Optional<Book> findBookById(Long id) {
        try {
            Book books = jdbcTemplate.queryForObject("SELECT * FROM book WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
            return Optional.ofNullable(books);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Book> findBookByTitle(String bookTitle) {
        return jdbcTemplate.query(connection -> {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM book WHERE bookTitle LIKE ?");
            statement.setString(1, bookTitle);
            return statement;
        }, new BeanPropertyRowMapper<>(Book.class));
    }

    @Override
    public List<Book> findBookByAuthor(String bookAuthor) {
        return jdbcTemplate.query(connection -> {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM book WHERE bookAuthor LIKE ?");
            statement.setString(1, bookAuthor);
            return statement;
        }, new BeanPropertyRowMapper<>(Book.class));
    }

    @Override
    public List<Book> findByTitleAndAuthor(String bookTitle, String bookAuthor) {
        return jdbcTemplate.query(connection -> {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM book WHERE bookTitle LIKE ? AND bookAuthor LIKE ?");
            statement.setString(1, bookTitle);
            statement.setString(2, bookAuthor);
            return statement;
        }, new BeanPropertyRowMapper<>(Book.class));
    }

    @Override
    public List<Book> getElectronicLibrary() {
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }
}
