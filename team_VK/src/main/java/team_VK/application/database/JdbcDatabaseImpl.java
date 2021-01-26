package team_VK.application.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import team_VK.application.core.domain.Book;

@Component
public class JdbcDatabaseImpl implements Database {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addBook(Book book) {

        jdbcTemplate.update(
                "INSERT INTO books (title, author) "
                        + "VALUES (?, ?)",
                book.getBookTitle(), book.getBookAuthor()
        );
    }

    @Override
    public void deleteBook(Book book) {

    }

    @Override
    public void getListBooks() {

        jdbcTemplate.update(
                "select * from books order by author;" );

    }



}
