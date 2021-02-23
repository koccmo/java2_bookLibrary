package lv.javaguru.app.database.repository;

import lv.javaguru.app.core.domain.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class OrmUserRepositoryImpl implements UserRepository{

	@Autowired
	private SessionFactory sessionFactory;


	public Long addUser (User user) {
		return (Long) sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public User getUserByUsername (String username) {
		String sql = "SELECT u FROM User u WHERE u.username = :username ";

		Query query = sessionFactory.getCurrentSession()
				.createQuery(sql);

		query.setParameter("username", username);

		return (User) query.getSingleResult();
	}

	public User getUserByNameAndSurname (String name, String lastName) {
		String sql = "SELECT u FROM User u WHERE u.name = :name AND u.lastName = :lastName";

		Query query = sessionFactory.getCurrentSession()
				.createQuery(sql);

		query.setParameter("name", name);
		query.setParameter("lastName", lastName);

		return (User) query.getSingleResult();
	}

	public boolean updateUserNameByUserId (Long id, String username) {
		String sql = "UPDATE User AS u SET u.username = :username WHERE u.id = :id";

		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("id", id);
		query.setParameter("username", username);

		int result = query.executeUpdate();

		return result == 1;
	}

	public boolean updateUserSurnameById (Long id, String surname) {
		String sql = "UPDATE User AS u SET u.surname = :surname WHERE u.id = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);

		query.setParameter("id", id);
		query.setParameter("surname", surname);
		int result = query.executeUpdate();
		return result == 1;
	}

	public User getUserById (Long id) {
		return sessionFactory.getCurrentSession()
				.get(User.class, id);
	}

	public List<User> getAllUsers () {
		return sessionFactory.getCurrentSession()
				.createQuery("SELECT u FROM User u", User.class)
				.getResultList();
	}

	public boolean removeUser (Long id) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"DELETE User AS u WHERE u.id = :id");
		query.setParameter("id", id);
		int result = query.executeUpdate();
		return result == 1;
	}
}
