package lv.javaguru.app.database;

import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.domain.Flight;
import lv.javaguru.app.core.domain.PersonType;
import lv.javaguru.app.core.domain.Ticket;
import lv.javaguru.app.core.domain.User;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class SqlDatabase {
	private static final String url = "jdbc:mysql://localhost:3306/java2";
	private static final String user = "root";
	private static final String password = "Passw0rd";


	public User getUserByNameAndSurname (User user) {
		String sql = String.format("SELECT * FROM users WHERE users.name=\"%s\" AND users.surname=\"%s\";",
				user.getName(),
				user.getSurname()
		);

		ResultSet s = executeQuery(sql);

		try {
			if (s.next()) {
				user.setId(Long.parseLong(s.getString("id")));
				PersonType personType = s.getString("person_type").equals("ADMIN") ? PersonType.ADMIN : PersonType.CLIENT;
				user.setPersonType(personType);
				return user;
			}

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return null;
	}

	public void addFlight (Flight flight) {
		addTicket(flight.getTicket());
		String sql = "INSERT INTO flights (user_id, ticket_id)\n" +
				String.format("VALUES (%s, %s);",
						flight.getUser().getId(),
						flight.getTicket().getId()
				);
		String message = execute(sql);
	}

	public void getAddedTicketId (Ticket ticket) {
		String sql = "SELECT * FROM tickets WHERE " +
				String.format("fromCountry=\"%s\" AND fromCity=\"%s\" AND toCoutry=\"%s\" AND toCity=\"%s\" AND date=\"%s\" AND seat=%s;",
						ticket.getOriginCountry(),
						ticket.getOriginCity(),
						ticket.getDestinationCountry(),
						ticket.getDestinationCity(),
						ticket.getDepartureDate(),
						ticket.getSeat()
				);
		ResultSet s = executeQuery(sql);
		try {
			if (s.next())
				ticket.setId(Long.parseLong(s.getString("id")));
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

	}

	public void deleteFlightById (Long id) {
		String sql = "DELETE FROM flights WHERE id=" + id + ";";

		execute(sql);
	}

	public void addTicket (Ticket ticket) {
		String sql = "INSERT INTO tickets (fromCountry, fromCity, toCoutry, toCity, date, seat)\n" +
				String.format("VALUES (\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",%s);",
						ticket.getOriginCountry(),
						ticket.getOriginCity(),
						ticket.getDestinationCountry(),
						ticket.getDestinationCity(),
						ticket.getDepartureDate(),
						ticket.getSeat()
				);
		execute(sql);
		getAddedTicketId(ticket);
	}

	public String addUser (User userToAdd) {
		String sql = "insert into users (name, surname, person_type)\n" +
				"values (\"" + userToAdd.getName() + "\", \"" + userToAdd.getSurname() + "\", \"CLIENT\");";
		String message = execute(sql);
		return message;
	}

	public void updateUserNameById (Long id, String name) {
		String sql = String.format("UPDATE users\n" +
				"SET users.name = \"%s\"\n" +
				"WHERE users.id=%s;", name, id);
		execute(sql);
	}

	public void updateUserSurnameById (Long id, String surname) {
		String sql = String.format("UPDATE users\n" +
				"SET users.surname = \"%s\"\n" +
				"WHERE users.id=%s;", surname, id);
		execute(sql);
	}

	public void updateTicketOriginByTicketId (Long id, String originCountry, String originCity) {
		String sql = String.format("UPDATE tickets\n" +
				"SET tickets.fromCountry = \"%s\",\n" +
				" tickets.fromCity = \"%s\"\n" +
				"WHERE tickets.id=%s;", originCountry, originCity, id);
		execute(sql);
	}

	public void updateTicketDestinationByTicketId (Long id, String destinationCountry, String destinationCity) {
		String sql = String.format("UPDATE tickets\n" +
				"SET tickets.toCounty = \"%s\",\n" +
				" tickets.toCity = \"%s\"\n" +
				"WHERE tickets.id=%s;", destinationCountry, destinationCity, id);
		execute(sql);
	}

	public void updateTicketDateByTicketId (Long id, String date) {
		String sql = String.format("UPDATE tickets\n" +
				"SET tickets.date = \"%s\"\n" +
				"WHERE tickets.id=%s;", date, id);
		execute(sql);
	}

	public void updateTicketSeatByTicketId (Long id, String seat) {
		String sql = String.format("UPDATE tickets\n" +
				"SET tickets.seat = \"%s\"\n" +
				"WHERE tickets.id=%s;", seat, id);
		execute(sql);
	}

	public User getUserById (Long id) {
		String sql = "SELECT * FROM users WHERE users.id=" + id + ";";
		ResultSet resultSet = executeQuery(sql);

		Long userId = null;
		String userName = null;
		String userSurname = null;
		PersonType personType = null;

		try {
			while (resultSet.next()) {

				userId = Long.parseLong(resultSet.getString("id"));
				userName = resultSet.getString("name");
				userSurname = resultSet.getString("surname");
				personType = resultSet.getString("person_type").equals("CLIENT") ?
						PersonType.CLIENT : PersonType.ADMIN;
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		if (userId == null || userName == null || userSurname == null || personType == null)
			return null;
		return new User(userId, userName, userSurname, personType);
	}

	public Flight getFlightById (Long id) {
		String sql = "SELECT flights.id, \n" +
				"users.id, \n" +
				"users.name, \n" +
				"users.surname, \n" +
				"users.person_type, \n" +
				"\n" +
				"tickets.id, \n" +
				"tickets.fromCountry, \n" +
				"tickets.fromCity, \n" +
				"tickets.toCoutry,\n" +
				"tickets.toCity,\n" +
				"tickets.date,\n" +
				"tickets.seat\n" +
				"\n" +
				"FROM flights\n" +
				"INNER JOIN users ON flights.user_id = users.id\n" +
				"inner join tickets on flights.ticket_id = tickets.id\n" +
				"WHERE flights.id=" + id + ";";

		ResultSet resultSet = executeQuery(sql);
		List<Flight> flights = new ArrayList<>();
		try {
			if (resultSet == null)
				return null;
			while (resultSet.next()) {
				User u = mapToUser(resultSet);
				Ticket t = mapToTicket(resultSet);
				Flight flight = new Flight(u, t);
				flight.setId(resultSet.getLong(1));

				flights.add(flight);


			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}

		if (flights.size() != 0)
			return flights.get(0);
		return null;
	}

	public List<User> getAllUsers () {
		String sql = "SELECT * FROM users;";
		ResultSet resultSet = executeQuery(sql);

		Long userId = null;
		String userName = null;
		String userSurname = null;
		PersonType personType = null;
		List<User> allUsers = new ArrayList<>();
		User u;
		try {
			while (resultSet.next()) {
				userId = Long.parseLong(resultSet.getString("id"));
				userName = resultSet.getString("name");
				userSurname = resultSet.getString("surname");
				personType = resultSet.getString("person_type").equals("CLIENT") ?
						PersonType.CLIENT : PersonType.ADMIN;
				u = new User(userId, userName, userSurname, personType);
				allUsers.add(u);
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return allUsers;
	}

	private User mapToUser (ResultSet set) throws SQLException {
		User user = new User();
		user.setId(set.getLong(2));
		user.setName(set.getString("name"));
		user.setSurname(set.getString("surname"));
		user.setPersonType(set.getString("person_Type").equals("CLIENT") ? PersonType.CLIENT : PersonType.ADMIN);
		return user;
	}

	private Ticket mapToTicket (ResultSet set) throws SQLException {
		Ticket ticket = new Ticket();
		ticket.setId(set.getLong(6));
		ticket.setOriginCountry(set.getString("fromCountry"));
		ticket.setOriginCity(set.getString("fromCity"));
		ticket.setDestinationCountry(set.getString("toCoutry"));
		ticket.setDestinationCity(set.getString("toCity"));

		String dateStr = set.getString("date").split(" ")[0];
		LocalDate date = LocalDate.parse(dateStr.trim());
		ticket.setDepartureDate(date);

		ticket.setSeat(set.getString("seat"));

		return ticket;
	}

	public List<Flight> getAllFlights () {
		String sql = "SELECT flights.id, \n" +
				"users.id, \n" +
				"users.name, \n" +
				"users.surname, \n" +
				"users.person_type, \n" +
				"\n" +
				"tickets.id, \n" +
				"tickets.fromCountry, \n" +
				"tickets.fromCity, \n" +
				"tickets.toCoutry,\n" +
				"tickets.toCity,\n" +
				"tickets.date,\n" +
				"tickets.seat\n" +
				"\n" +
				"FROM flights\n" +
				"INNER JOIN users ON flights.user_id = users.id\n" +
				"inner join tickets on flights.ticket_id = tickets.id;";
		ResultSet resultSet = executeQuery(sql);


		List<Flight> flights = new ArrayList<>();
		try {
			while (resultSet.next()) {
				User u = mapToUser(resultSet);
				Ticket t = mapToTicket(resultSet);
				Flight flight = new Flight(u, t);
				flight.setId(resultSet.getLong(1));

				flights.add(flight);

			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return flights;
	}

	public boolean isUserFlight (Long id, User user) {
		return getAllUserFlights(user).stream()
				.anyMatch(flight -> flight.getId().equals(id));
	}

	public List<Flight> getAllUserFlights (User user) {
		String sql = "SELECT flights.id, \n" +
				"users.id, \n" +
				"users.name, \n" +
				"users.surname, \n" +
				"users.person_type, \n" +
				"\n" +
				"tickets.id, \n" +
				"tickets.fromCountry, \n" +
				"tickets.fromCity, \n" +
				"tickets.toCoutry,\n" +
				"tickets.toCity,\n" +
				"tickets.date,\n" +
				"tickets.seat\n" +
				"\n" +
				"FROM flights\n" +
				"INNER JOIN users ON flights.user_id = users.id\n" +
				"inner join tickets on flights.ticket_id = tickets.id\n" +
				String.format("WHERE users.id=%s " +
						"AND users.name=\"%s\" " +
						"AND users.surname=\"%s\";", user.getId(), user.getName(), user.getSurname());
		ResultSet resultSet = executeQuery(sql);


		List<Flight> flights = new ArrayList<>();
		try {
			while (resultSet.next()) {
				User u = mapToUser(resultSet);
				Ticket t = mapToTicket(resultSet);
				Flight flight = new Flight(u, t);
				flight.setId(Long.parseLong(resultSet.getString(1)));

				flights.add(flight);

			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return flights;
	}


	public String removeUser (Long id) {
		String sql = "DELETE FROM users WHERE id=\"" + id.toString() + "\";";

		return execute(sql);
	}

	private String execute (String sql) {
		Connection connection = null;
		Statement statement = null;
		String message = null;

		try {
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
			statement.execute(sql);


		} catch (SQLException e) {
			message = e.toString();
		}
		return message;
	}

	private ResultSet executeQuery (String sql) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;// = new ArrayList<>();

		try {
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return resultSet;
	}
}
