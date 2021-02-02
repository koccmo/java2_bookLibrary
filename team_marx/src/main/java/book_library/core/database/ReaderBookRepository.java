package book_library.core.database;

import book_library.core.domain.ReaderBook;
import org.hibernate.SessionFactory;
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
}
