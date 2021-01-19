package lv.javaguru.app.database;

import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.domain.Ticket;

import java.util.List;
import java.util.Optional;

public interface Database {
	User getCurrentPerson ();

	void setCurrentUser (User currentUser);


	void addUser (User user);

	void removeUserById (Long id);

	Optional<User> getUser (User user);

	List<User> getAllUsers ();

	User getUserById (Long id);

	boolean isContainUserWithId (Long id);


	void addTicket (User user, Ticket ticket);

	void removeTicketById (Long id);

	List<Ticket> getAllUserTickets (User user);

	List<Ticket> getAllTickets ();

	Ticket getTicketById (Long id);

	boolean isUserHaveTicketWithId (Long id);

	boolean isContainTicketWithId (Long id);
}
