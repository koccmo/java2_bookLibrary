package lv.javaguru.app.database;

import lv.javaguru.app.core.domain.Reservation;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.domain.Ticket;

import java.util.List;
import java.util.Optional;

public interface Database {
	void addReservation (Reservation reservation);

	void removeReservation (Reservation reservation);

	void removeReservationById (Long id);

	Reservation getReservationById (Long id);

	List<Reservation> getAllReservations ();

	List<Reservation> getAllUserReservations (User user);

	boolean containsKey (Long id);

	Ticket getReservationTicket (Long id);

	User getReservationUser (Long id);

	boolean isUsersReservation (Long id, User user);


	/*
	void addTicket (User user, Ticket ticket);

	void removeTicketById (Long id);

	List<Ticket> getAllUserTickets (User user);

	List<Ticket> getAllTickets ();

	Ticket getTicketById (Long id);

	boolean isUserHaveTicketWithId (Long id);

	boolean isContainTicketWithId (Long id);*/
}
