package lv.javaguru.app.database;

import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.domain.Ticket;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryDatabase implements Database {

	private final Map<User, List<Ticket>> reservations = new HashMap<>();
	private static Long nextId = 1L;
	private static Long nextPersonId = 1L;
	private User currentUser;


	public User getCurrentPerson () {
		return currentUser;
	}

	public void setCurrentUser (User currentUser) {
		this.currentUser = currentUser;
	}


	@Override
	public User getUserById (Long id) {
		return reservations.keySet().stream()
				.filter(person -> person.getId().equals(id))
				.findFirst()
				.orElseGet(null);
	}

	@Override
	public boolean isContainUserWithId (Long id) {
		return reservations.keySet().stream()
				.anyMatch(person -> person.getId().equals(id));
	}

	@Override
	public void addUser (User user) {
		if (!reservations.containsKey(user)) {
			//user.setId(nextPersonId++);
			reservations.put(user, new ArrayList<>());
		}
	}


	@Override
	public void removeUserById (Long id) {
		if (isContainUserWithId(id)) {
			User user = getUserById(id);

			reservations.remove(user);
		}
	}

	@Override
	public Optional<User> getUser (User user) {
		return reservations.keySet().stream()
				.filter(user1 -> user1.equals(user))
				.findFirst();

		//	return reservations.keySet().stream()
		//			.filter(person -> person.equals(user))
		//			.findFirst()
		//			.orElse(null);
	}

	@Override
	public List<User> getAllUsers () {
		return reservations.keySet().stream().map(user -> user).collect(Collectors.toList());
	}


	@Override
	public void addTicket (User user, Ticket ticket) {
		if (reservations.containsKey(user)) {
			ticket.setId(nextId++);
			reservations.get(user).add(ticket);
		}
	}


	@Override
	public void removeTicketById (Long id) {
		reservations.get(currentUser).removeIf(ticket -> ticket.getId().equals(id));
	}


	@Override
	public List<Ticket> getAllUserTickets (User user) {
		return reservations.get(user);
	}

	@Override
	public List<Ticket> getAllTickets () {
		return reservations.values().stream()
				.flatMap(List::stream)
				.collect(Collectors.toList());
	}


	@Override
	public Ticket getTicketById (Long id) {
		return getAllTickets().stream()
				.filter(ticket -> ticket.getId().equals(id))
				.findFirst()
				.orElse(null);
	}

	@Override
	public boolean isUserHaveTicketWithId (Long id) {
		return reservations.get(currentUser).stream().anyMatch(ticket -> ticket.getId().equals(id));
	}


	@Override
	public boolean isContainTicketWithId (Long id) {
		return getAllTickets().stream()
				.anyMatch(ticket -> ticket.getId().equals(id));
	}
}
