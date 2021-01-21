package book_library.core.database;

import book_library.core.domain.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcDatabaseImp implements Database{
    @Override
    public void save(Book book) {

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
