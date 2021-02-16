package lesson_12.core.database.book;

import lesson_12.core.domain.Book;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
@Profile("orm")
@Transactional
public class OrmElectronicLibraryRepositoryImpl implements ElectronicLibraryRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveBook(Book book) {
        sessionFactory.getCurrentSession().save(book);
    }

    @Override
    public boolean deleteBook(Book book) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "DELETE Book where bookTitle = : bookTitle AND bookAuthor = : bookAuthor");
        query.setParameter("bookTitle", book.getBookTitle());
        query.setParameter("bookAuthor", book.getBookAuthor());
        return query.executeUpdate() > 0;
    }

    @Override
    public boolean deleteBookById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "DELETE Book WHERE id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean deleteBookByTitle(String bookTitle) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "DELETE Book WHERE bookTitle = :bookTitle");
        query.setParameter("bookTitle", bookTitle);
        int result = query.executeUpdate();
        return result > 0;
    }

    @Override
    public boolean deleteBookByAuthor(String bookAuthor) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "DELETE Book WHERE bookAuthor = :bookAuthor");
        query.setParameter("bookAuthor", bookAuthor);
        int result = query.executeUpdate();
        return result > 0;
    }

    @Override
    public Optional<Book> findBookById(Long id) {
        try {
            Book book = sessionFactory.getCurrentSession().find(Book.class, id);
            return Optional.ofNullable(book);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Book> findBookByTitle(String bookTitle) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT b FROM Book b WHERE bookTitle = :bookTitle");
        query.setParameter("bookTitle", bookTitle);
        return query.getResultList();
    }

    @Override
    public List<Book> findBookByAuthor(String bookAuthor) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT b FROM Book b WHERE bookAuthor = :bookAuthor");
        query.setParameter("bookAuthor", bookAuthor);
        return query.getResultList();
    }

    @Override
    public List<Book> findByTitleAndAuthor(String bookTitle, String bookAuthor) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT b FROM Book b WHERE bookTitle = :bookTitle AND bookAuthor = :bookAuthor");
        query.setParameter("bookTitle", bookTitle);
        query.setParameter("bookAuthor", bookAuthor);
        return query.getResultList();
    }

    @Override
    public List<Book> getElectronicLibrary() {
        return sessionFactory.getCurrentSession().getSession().createQuery("FROM Book").list();
    }
}
