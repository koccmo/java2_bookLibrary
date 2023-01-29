package bookLibrary.core.dataBase;

import bookLibrary.core.domain.Reader;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public boolean deleteById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("delete Reader Where id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result == 1;
    }

    public boolean checkByIndexReaderAlreadyRegistered(String name, String lastName, Long personalCode) {
        Query query = sessionFactory.getCurrentSession().createQuery("Select b FROM Reader b WHERE first_name = :name" +
                " AND last_name = :lastName AND personal_code = :personalCode");
        query.setParameter("name", name);
        query.setParameter("lastName", lastName);
        query.setParameter("personalCode", personalCode);
        List<Reader> readers = query.getResultList();
        return !readers.isEmpty();
    }

    public List<Reader> findByName(String name) {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT b FROM Reader b WHERE first_name = :name", Reader.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Reader> findByLastName(String lastName) {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT b FROM Reader b WHERE last_name = :lastName", Reader.class)
                .setParameter("last_name", lastName)
                .getResultList();
    }

    public List<Reader> findByNameAndLastName(String name, String lastName) {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT b FROM Reader b WHERE first_name = :name AND last_name = :lastName",
                        Reader.class)
                .setParameter("name", name)
                .setParameter("lastName", lastName)
                .getResultList();
    }



}
