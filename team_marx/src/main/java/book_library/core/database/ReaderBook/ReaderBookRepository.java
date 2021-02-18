package book_library.core.database.ReaderBook;

import book_library.core.domain.ReaderBook;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

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

    public boolean isBookInLibrary(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT b FROM ReaderBook b WHERE book_id = :book_id AND book_return_date IS NULL");
        query.setParameter("book_id", id);
        return !query.getResultList().isEmpty();
    }

    public void returnBook(ReaderBook readerBook) {
    }
}
