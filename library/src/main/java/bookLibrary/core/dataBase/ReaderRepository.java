package bookLibrary.core.dataBase;

import bookLibrary.core.domain.Reader;
import org.hibernate.SessionFactory;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class ReaderRepository {
    @Autowired
    private SessionFactory sessionFactory;


    public void save(Reader reader) {
        sessionFactory.getCurrentSession().save(reader);
    }

    public Reader findById(Long id) {
        return sessionFactory.getCurrentSession().get(Reader.class, id);
    }

}
