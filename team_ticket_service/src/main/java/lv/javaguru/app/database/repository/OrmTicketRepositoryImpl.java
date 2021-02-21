package lv.javaguru.app.database.repository;

import lv.javaguru.app.core.domain.Ticket;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
@Transactional
public class OrmTicketRepositoryImpl implements TicketRepository {

	@Autowired
	private SessionFactory sessionFactory;


	public Long addTicket (Ticket ticket) {
		return (Long) sessionFactory.getCurrentSession()
				.save(ticket);
	}

	public Ticket getTicketById (Long id) {
		return sessionFactory.getCurrentSession()
				.get(Ticket.class, id);
	}


	@Override
	public boolean deleteTicketById (Long id) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"DELETE Ticket WHERE id = :id");
		query.setParameter("id", id);
		int result = query.executeUpdate();

		return result == 1;
	}

	public boolean updateTicketDateByTicketId (Long id, Date date) {
		String sql = "UPDATE Ticket t SET t.departureDate = :date WHERE t.id = :id";

		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("id", id);
		query.setParameter("date", date);

		int result = query.executeUpdate();

		return result == 1;
	}

	public boolean updateTicketDestinationByTicketId (Long id, String destinationCountry, String destinationCity) {
		String sql = "UPDATE Ticket t SET t.destinationCountry = :destinationCountry, t.destinationCity = :destinationCity WHERE t.id = :id";

		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("id", id);
		query.setParameter("destinationCountry", destinationCountry);
		query.setParameter("destinationCity", destinationCity);

		int result = query.executeUpdate();

		return result == 1;
	}

	public boolean updateTicketOriginByTicketId (Long id, String originCountry, String originCity) {
		String sql = "UPDATE Ticket t SET t.originCountry = :originCountry, t.originCity = :originCity WHERE t.id = :id";

		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("id", id);
		query.setParameter("originCountry", originCountry);
		query.setParameter("originCity", originCity);

		int result = query.executeUpdate();

		return result == 1;
	}

	public boolean updateTicketSeatByTicketId (Long id, String seat) {
		String sql = "UPDATE Ticket t SET t.seat = :seat WHERE t.id = :id";

		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("id", id);
		query.setParameter("seat", seat);

		int result = query.executeUpdate();

		return result == 1;
	}
}
