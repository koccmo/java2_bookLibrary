package lv.javaguru.app.database.repository;

import lv.javaguru.app.core.domain.Flight;
import lv.javaguru.app.core.domain.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Component
@Transactional
public class OrmFlightRepositoryImpl implements FlightRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public Long addFlight (Flight flight) {
		return (Long) sessionFactory.getCurrentSession().save(flight);
	}

	public boolean deleteFlightById (Long id) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"DELETE Flight WHERE id = :id");
		query.setParameter("id", id);
		int result = query.executeUpdate();
		return result == 1;
	}

	@Override
	public boolean deleteFlightsByUserId (Long id) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"DELETE Flight WHERE users_id = :id");
		query.setParameter("id", id);
		int result = query.executeUpdate();
		return result == 1;
	}

	public Flight getFlightById (Long id) {
		return sessionFactory.getCurrentSession()
				.get(Flight.class, id);
	}

	public List<Flight> getAllFlights () {
		return sessionFactory.getCurrentSession()
				.createQuery("SELECT f FROM Flight f", Flight.class)
				.getResultList();
	}

	public boolean isUserFlight (Long id, User user) {
		return getAllUserFlights(user).stream()
				.anyMatch(flight -> flight.getId().equals(id));
	}

	public List<Flight> getAllUserFlights (User user) {
		String sql = "SELECT f FROM Flight f " +
				"WHERE users_id = :id";

		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("id", user.getId());
		return query.getResultList();
	}
}
