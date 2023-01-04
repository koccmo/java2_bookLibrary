package bookLibrary.core.dataBase;

import bookLibrary.core.domain.ReaderBook;
import jakarta.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
