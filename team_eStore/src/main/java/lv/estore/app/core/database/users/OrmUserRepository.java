package lv.estore.app.core.database.users;

import lv.estore.app.core.domain.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class OrmUserRepository implements UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long addUser(User user) {
        return (Long) sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }

    @Override
    public boolean removeUserByEmail(String email) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "DELETE User where email = :email");
        query.setParameter("email", email);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean removeUserById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "DELETE User where id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public User findUserById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT u FROM User u where id = :id");
        query.setParameter("id", id);
        return (User) query.getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public List<User> findUsersByLastName(String lastName) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT u FROM User u where lastName = :lastName");
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }

    @Override
    public boolean updateUserById(Long id, String firstName, String lastName, String email) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "UPDATE User SET firstName = :firstName, " +
                        "lastName = :lastName, email = :email WHERE id = :id");
        query.setParameter("id", id);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        query.setParameter("email", email);
        int result = query.executeUpdate();
        return result == 1;
    }
}
