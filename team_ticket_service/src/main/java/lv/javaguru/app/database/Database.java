package lv.javaguru.app.database;

import lv.javaguru.app.core.domain.Flight;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.domain.Ticket;

import java.util.List;

public interface Database {
	void addReservation (Flight flight);

	void removeReservation (Flight flight);

	void removeReservationById (Long id);

	Flight getFlightById (Long id);

	List<Flight> getAllReservations ();

	List<Flight> getAllUserReservations (User user);

	boolean containsKey (Long id);

	Ticket getTicketByFlightId (Long id);

	User getUserByFlightId (Long id);

	boolean isUsersReservation (Long id, User user);

	boolean isContainTicket (Ticket ticket);


	/*
	void addTicket (User user, Ticket ticket);

	void removeTicketById (Long id);

	List<Ticket> getAllUserTickets (User user);

	List<Ticket> getAllTickets ();

	Ticket getTicketById (Long id);

	boolean isUserHaveTicketWithId (Long id);

	boolean isContainTicketWithId (Long id);*/
}
