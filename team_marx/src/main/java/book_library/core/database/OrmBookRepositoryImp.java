package book_library.core.database;

import book_library.core.domain.Book;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class OrmBookRepositoryImp implements BookRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Book book) {
        sessionFactory.getCurrentSession().save(book);
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
    public boolean isSuchIdPresentsInDatabase(Long idToCheck) {
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
