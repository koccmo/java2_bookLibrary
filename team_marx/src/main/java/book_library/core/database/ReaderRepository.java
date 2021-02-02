package book_library.core.database;

import book_library.core.domain.Reader;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ReaderRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public void save (Reader reader) {
        sessionFactory.getCurrentSession().save(reader);
    }

    public Reader findById(Long id) {
        return sessionFactory.getCurrentSession().get(Reader.class, id);
    }
}
