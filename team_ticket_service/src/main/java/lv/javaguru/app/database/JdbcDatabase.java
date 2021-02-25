package lv.javaguru.app.database;

import lv.javaguru.app.core.domain.Flight;
import lv.javaguru.app.core.domain.PersonType;
import lv.javaguru.app.core.domain.Ticket;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.database.rowmappers.FlightRowMapper;
import lv.javaguru.app.database.rowmappers.TicketRowMapper;
import lv.javaguru.app.database.rowmappers.UserRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;


public class JdbcDatabase {

	private JdbcTemplate jdbcTemplate;

	public User getUserByNameAndSurname (User user) {
		String sql = "SELECT * FROM users WHERE users.name = ?  AND users.surname = ?";
		Object[] args = new Object[]{
				user.getUsername(),
				user.getPassword()
		};

		List<User> users = jdbcTemplate.query(sql, args, new UserRowMapper());

		return users.size() > 0 ? users.get(0) : null;
	}

	public void addFlight (Flight flight) {
		String sql = "INSERT INTO flights (users_id, tickets_id) " +
				"VALUES (?, ?)";
		Object[] args = new Object[]{
				flight.getUser().getId(),
				flight.getTicket().getId()
		};

		jdbcTemplate.update(sql, args);
	}

	public boolean deleteFlightById (Long id) {
		String sql = "DELETE FROM flights WHERE flights.id = ?";
		Object[] args = new Object[]{id};

		return jdbcTemplate.update(sql, args) == 1;
	}



	public boolean addUser (User userToAdd) {
		String sql = "INSERT INTO users (name, surname) " +
				"VALUES (?, ?)";
			//	"VALUES (?, ?, ?)";
		Object[] args = new Object[]{
				userToAdd.getUsername(),
				userToAdd.getPassword(),
			//	userToAdd.getPersonType() == PersonType.CLIENT ? "CLIENT" : "ADMIN"
		};

		return jdbcTemplate.update(sql, args) == 1;
	}
	public User getUserById (Long id) {
		String sql = "SELECT * FROM users WHERE users.id = ?";
		Object[] args = new Object[]{id};

		List<User> users = jdbcTemplate.query(sql, args, new UserRowMapper());

		return users.size() > 0 ? users.get(0) : null;
	}

	public List<User> getAllUsers () {
		String sql = "SELECT * FROM users;";

		return jdbcTemplate.query(sql, new UserRowMapper());
	}

	public boolean removeUser (Long id) {
		String sql = "DELETE FROM users WHERE id = ?";
		Object[] args = new Object[]{id};

		return jdbcTemplate.update(sql, args) == 1;
	}

	public boolean updateUserNameByUserId (Long id, String name) {
		String sql = "UPDATE users SET users.name = ? WHERE users.id = ?";
		Object[] args = new Object[]{name, id};

		return jdbcTemplate.update(sql, args) == 1;
	}

	public boolean updateUserSurnameById (Long id, String surname) {
		String sql = "UPDATE users SET users.surname = ? WHERE users.id = ?";
		Object[] args = new Object[]{surname, id};

		return jdbcTemplate.update(sql, args) == 1;
	}

	public Ticket getAddedTicketId (Ticket ticket) {
	//	String sql = "SELECT * FROM tickets WHERE fromCountry = ? AND fromCity = ? AND toCountry = ? AND toCity = ? AND date = ? AND seat = ?";
		String sql = "SELECT * FROM tickets WHERE fromCountry = ? AND fromCity = ? AND toCountry = ? AND toCity = ? AND seat = ?";
		Object[] args = new Object[]{
				ticket.getOriginCountry(),
				ticket.getOriginCity(),
				ticket.getDestinationCountry(),
				ticket.getDestinationCity(),
			//	ticket.getDepartureDate(),
				ticket.getSeat()
		};
		List<Ticket> tickets = jdbcTemplate.query(sql, args, new TicketRowMapper());

		return tickets.size() > 0 ? tickets.get(0) : null;
	}

	public void addTicket (Ticket ticket) {
		//String sql = "INSERT INTO tickets (fromCountry, fromCity, toCountry, toCity, date, seat) "
		String sql = "INSERT INTO tickets (fromCountry, fromCity, toCountry, toCity, seat) "
				+ "VALUES (?, ?, ?, ?, ?)";
		Object[] args = new Object[]{
				ticket.getOriginCountry(),
				ticket.getOriginCity(),
				ticket.getDestinationCountry(),
				ticket.getDestinationCity(),
			//	ticket.getDepartureDate(),
				ticket.getSeat()
		};

		jdbcTemplate.update(sql, args);
	}


	public boolean updateTicketOriginByTicketId (Long id, String originCountry, String originCity) {
		String sql = "UPDATE tickets SET tickets.fromCountry = ?, tickets.fromCity = ? WHERE tickets.id = ?";
		Object[] args = new Object[]{originCountry, originCity, id};

		return jdbcTemplate.update(sql, args) == 1;
	}

	public boolean updateTicketDestinationByTicketId (Long id, String destinationCountry, String destinationCity) {
		String sql = "UPDATE tickets SET tickets.toCountry = ?, tickets.toCity = ? WHERE tickets.id = ?";
		Object[] args = new Object[]{destinationCountry, destinationCity, id};

		return jdbcTemplate.update(sql, args) == 1;
	}

	public boolean updateTicketDateByTicketId (Long id, String date) {
		String sql = "UPDATE tickets SET tickets.date = ? WHERE tickets.id = ?";
		Object[] args = new Object[]{date, id};

		return jdbcTemplate.update(sql, args) == 1;
	}

	public boolean updateTicketSeatByTicketId (Long id, String seat) {
		String sql = "UPDATE tickets SET tickets.seat = ? WHERE tickets.id = ?";
		Object[] args = new Object[]{seat, id};

		return jdbcTemplate.update(sql, args) == 1;
	}






	public Flight getFlightById (Long id) {
		String sql = "SELECT flights.id, users.*, tickets.* " +
				"FROM flights " +
				"INNER JOIN users ON flights.users_id = users.id " +
				"INNER JOIN tickets ON flights.tickets_id = tickets.id " +
				"WHERE flights.id = ?";
		Object[] args = new Object[]{id};

		List<Flight> flights = jdbcTemplate.query(sql, args, new FlightRowMapper());

		return flights.size() > 0 ? flights.get(0) : null;
	}


	public List<Flight> getAllFlights () {
		String sql = "SELECT flights.id, users.*, tickets.* " +
				"FROM flights " +
				"INNER JOIN users ON flights.users_id = users.id " +
				"INNER JOIN tickets ON flights.tickets_id = tickets.id";

		return jdbcTemplate.query(sql, new FlightRowMapper());
	}

	public boolean isUserFlight (Long id, User user) {
		return getAllUserFlights(user).stream()
				.anyMatch(flight -> flight.getId().equals(id));
	}

	public List<Flight> getAllUserFlights (User user) {
		String sql = "SELECT flights.id, users.*, tickets.* " +
				"FROM flights " +
				"INNER JOIN users ON flights.users_id = users.id " +
				"INNER JOIN tickets ON flights.tickets_id = tickets.id " +
				"WHERE users.id = ? " +
				"AND users.name = ? " +
				"AND users.surname = ?";
		Object[] args = new Object[]{user.getId(), user.getUsername(), user.getPassword()};

		return jdbcTemplate.query(sql, args, new FlightRowMapper());
	}
}
