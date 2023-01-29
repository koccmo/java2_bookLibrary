package bookLibrary.core.dataBase;

import bookLibrary.core.domain.Book;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Component
@Transactional
public class OrmBookRepositoryImpl implements DataBase{
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public void addBook(Book book) {
        sessionFactory.getCurrentSession().save(book);
    }

    @Override
    public void deleteBook(Long id) {
        sessionFactory.getCurrentSession()
                .createQuery("DELETE FROM Book WHERE id =:id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void finishWork() {
        System.exit(0);
    }

    @Override
    public List<String> getAllBooksTitle() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT title FROM Book", String.class)
                .getResultList();
    }

    @Override
    public List<Book> findByAuthor(String author) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select b FROM Book b where author = :author");
        query.setParameter("author", author);
        return query.getResultList();
    }

    @Override
    public List<Book> findByTitle(String title) {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT b FROM Book b WHERE title =:title", Book.class)
                .setParameter("title", title)
                .getResultList();
    }

    @Override
    public List<Book> findByAuthorAndTitle(String author, String title) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("SELECT b FROM Book b WHERE author =:author AND title =:title");
        query.setParameter("author", author);
        query.setParameter("title", title);
        return query.getResultList();
    }

    @Override
    public Long getBookId(String author, String title) {
         Query query = sessionFactory.getCurrentSession()
                .createQuery("SELECT id FROM Book WHERE author =:author AND title =:title");
         query.setParameter("author", author);
         query.setParameter("title", title);
         List<Long> id = query.list();
         return id.get(0);
    }

    @Override
    public boolean hasBookInLibrary(Book book) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("SELECT b FROM Book b WHERE author =: author AND title =:title");
        query.setParameter("author", book.getAuthor());
        query.setParameter("title", book.getTitle());
        List<Book> books = query.getResultList();
        return !books.isEmpty();
    }

    @Override
    public boolean hasBookInLibraryCheckById(Long id) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("SELECT b FROM Book b WHERE id =:id");
        query.setParameter("id", id);
        List<Book> books = query.getResultList();
        return !books.isEmpty();
    }
}
