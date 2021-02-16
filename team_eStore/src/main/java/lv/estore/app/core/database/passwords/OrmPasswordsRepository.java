package lv.estore.app.core.database.passwords;

import lv.estore.app.core.domain.Password;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class OrmPasswordsRepository implements PasswordsRepository {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public Long addPassword(Password password) {
        return (Long) sessionFactory.getCurrentSession().save(password);
    }

    @Override
    public boolean addPasswordByUserId(Long id, String password) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "INSERT INTO Password (password, user_id) VALUES (password =: password, id = :id)");
        query.setParameter("password", password);
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean updatePasswordByUserId(Long id, String password) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "UPDATE Password SET password =: password WHERE user_id = :id");
        query.setParameter("password", password);
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result == 1;
    }
}
