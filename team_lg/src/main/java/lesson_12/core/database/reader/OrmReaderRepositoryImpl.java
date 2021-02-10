package lesson_12.core.database.reader;

import lesson_12.core.domain.Reader;
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
public class OrmReaderRepositoryImpl implements ReaderRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveReader(Reader reader) {
        sessionFactory.getCurrentSession().save(reader);
    }

    @Override
    public boolean deleteReader(Reader reader) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "DELETE Reader where readerFirstName = : readerFirstName AND readerLastName = : readerLastName AND readerPersonalCode = : readerPersonalCode");
        query.setParameter("readerFirstName", reader.getReaderFirstName());
        query.setParameter("readerLastName", reader.getReaderLastName());
        query.setParameter("readerPersonalCode", reader.getReaderPersonalCode());
        return query.executeUpdate() > 0;
    }

    @Override
    public boolean deleteReaderById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "DELETE Reader WHERE id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean deleteReaderByFirstName(String readerFirstName) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "DELETE Reader WHERE readerFirstName = :readerFirstName");
        query.setParameter("readerFirstName", readerFirstName);
        int result = query.executeUpdate();
        return result > 0;
    }

    @Override
    public boolean deleteReaderByLastName(String readerLastName) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "DELETE Reader WHERE readerLastName = :readerLastName");
        query.setParameter("readerLastName", readerLastName);
        int result = query.executeUpdate();
        return result > 0;
    }

    @Override
    public Optional<Reader> findReaderById(Long id) {
        try {
            Reader reader = sessionFactory.getCurrentSession().find(Reader.class, id);
            return Optional.ofNullable(reader);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Reader> findReaderByFirstName(String readerFirstName) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT r FROM Reader r WHERE readerFirstName = :readerFirstName");
        query.setParameter("readerFirstName", readerFirstName);
        return query.getResultList();
    }

    @Override
    public List<Reader> findReaderByLastName(String readerLastName) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT r FROM Reader r WHERE readerLastName = :readerLastName");
        query.setParameter("readerLastName", readerLastName);
        return query.getResultList();
    }

    @Override
    public List<Reader> findReaderByPersonalCode(String readerPersonalCode) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT r FROM Reader r WHERE readerPersonalCode = :readerPersonalCode");
        query.setParameter("readerPersonalCode", readerPersonalCode);
        return query.getResultList();
    }

    @Override
    public List<Reader> findByFirstNameAndLastName(String readerFirstName, String readerLastName) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT r FROM Reader r WHERE readerFirstName = :readerFirstName AND readerLastName = :readerLastName");
        query.setParameter("readerFirstName", readerFirstName);
        query.setParameter("readerLastName", readerLastName);
        return query.getResultList();
    }

    @Override
    public List<Reader> getReaders() {
        return sessionFactory.getCurrentSession().getSession().createQuery("FROM Reader").list();
    }

    @Override
    public boolean containsReader(Reader reader) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT r FROM Reader r where readerFirstName = : readerFirstName AND readerLastName = : readerLastName AND readerPersonalCode = : readerPersonalCode");
        query.setParameter("readerFirstName", reader.getReaderFirstName());
        query.setParameter("readerLastName", reader.getReaderLastName());
        query.setParameter("readerPersonalCode", reader.getReaderPersonalCode());
        return query.getResultList().size() > 0;
        }

}
