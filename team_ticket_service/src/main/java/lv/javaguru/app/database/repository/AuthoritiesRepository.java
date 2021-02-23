package lv.javaguru.app.database.repository;

import lv.javaguru.app.core.domain.Flight;
import lv.javaguru.app.core.domain.Role;
import lv.javaguru.app.core.domain.Ticket;
import lv.javaguru.app.core.domain.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

@Component
@Transactional
public class AuthoritiesRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public String addAuthority (Role role) {
		return (String) sessionFactory.getCurrentSession().save(role);
	}

	public Role getUserRoleByUsername (String username) {
		return sessionFactory.getCurrentSession()
				.get(Role.class, username);
	}

	public boolean deleteRoleByUsername (String username) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"DELETE Role WHERE username = :username");
		query.setParameter("username", username);
		int result = query.executeUpdate();

		return result == 1;
	}
}
