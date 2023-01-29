package bookLibrary.core.dataBase;

import bookLibrary.core.domain.Book;
import bookLibrary.core.domain.Reader;
import bookLibrary.core.domain.ReaderBook;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class ReaderBookRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public void save(ReaderBook readerBook) {
        sessionFactory.getCurrentSession().save(readerBook);
    }

    public ReaderBook getById(Long id) {
        return sessionFactory.getCurrentSession().get(ReaderBook.class, id);
    }

    public boolean hasReaderInDataBase(Long readerId) {
        List<Reader> readers = new ArrayList<>();
        readers.add(sessionFactory.getCurrentSession().get(Reader.class, readerId));
        return !readers.isEmpty();
    }

    public boolean hasBookInDataBaseCheckById(Long bookId) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("SELECT b FROM Book b WHERE id = :id")
                .setParameter("id", bookId);
        List books = query.getResultList();
        return !books.isEmpty();
    }

    public Reader readerGetById(Long readerId) {
        return sessionFactory.getCurrentSession().get(Reader.class, readerId);
    }

    public Book bookGetById(Long bookId) {
        return sessionFactory.getCurrentSession().get(Book.class, bookId);
    }

    public boolean checkReturnDateEmpty(Long bookId) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("SELECT b FROM ReaderBook b WHERE book_id = :bookId AND book_return_date is NULL")
                .setParameter("bookId", bookId);
//                .setParameter("returnDate", null);
        List<ReaderBook> readerBooks = query.getResultList();
        return readerBooks.isEmpty();
    }


}
