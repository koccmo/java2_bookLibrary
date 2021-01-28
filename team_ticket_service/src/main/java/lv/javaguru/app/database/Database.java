package lv.javaguru.app.database;

import lv.javaguru.app.core.domain.Flight;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.domain.Ticket;

import java.util.List;
import java.util.Optional;

public interface Database {
	User getCurrentUser ();

	void setCurrentUser (User currentUser);

	void addTicket (Ticket ticket);

	Optional<Ticket> getTicket (Ticket ticket);

	List<Ticket> getAllTickets ();

	Ticket getTicketById (Long id);

	boolean ticketTableContainsTicket (Long id);

	boolean deleteTicketById (Long id);

	void addUser (User user);

	Optional<User> getUserByNameAndSurname (User user);

	List<User> getAllUsers ();

	User getUserById (Long id);

	boolean userTableContainsUser (Long id);

	boolean deleteUserById (Long id);


	void addFlight (Flight flight);

	void removeFlightById (Long id);

	Flight getFlightById (Long id);

	List<Flight> getAllFlights ();

	List<Flight> getAllUserFlights (User user);

	boolean flightTableContainsId (Long id);

	Ticket getTicketByFlightId (Long id);

	User getUserByFlightId (Long id);

	boolean isUsersFlight (Long id, User user);

	boolean isFlightTableContainsTicket (Ticket ticket);
}
