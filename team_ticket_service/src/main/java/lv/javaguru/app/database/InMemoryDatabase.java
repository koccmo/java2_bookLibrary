package lv.javaguru.app.database;

import lv.javaguru.app.core.domain.Flight;
import lv.javaguru.app.core.domain.Ticket;
import lv.javaguru.app.core.domain.User;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryDatabase implements Database {
	private final Map<Long, Ticket> ticketTable = new HashMap<>();
	private final Map<Long, User> userTable = new HashMap<>();
	private final Map<Long, Flight> flightTable = new HashMap<>();

	private static Long nextTicketId = 1L;
	private static Long nextUserId = 1L;
	private static Long nextFlightId = 1L;

	private User currentUser;


	public User getCurrentUser () {
		return currentUser;
	}

	public void setCurrentUser (User currentUser) {
		this.currentUser = currentUser;
	}


	public void addTicket (Ticket ticket) {
		if (!ticketTable.containsKey(ticket.getId())) {
			ticket.setId(nextTicketId++);
			ticketTable.put(ticket.getId(), ticket);
		}
	}

	public Optional<Ticket> getTicket (Ticket ticket) {
		return ticketTable.values().stream()
				.filter(t -> t.equals(ticket))
				.findFirst();
	}

	public List<Ticket> getAllTickets () {
		return new ArrayList<>(ticketTable.values());
	}

	public Ticket getTicketById (Long id) {
		return ticketTable.get(id);
	}

	public boolean ticketTableContainsTicket (Long id) {
		return ticketTable.containsKey(id);
	}

	public boolean deleteTicketById (Long id) {
		Ticket ticket = ticketTable.get(id);
		return ticketTable.remove(id, ticket);
	}


	public void addUser (User user) {
		if (!userTable.containsKey(user.getId())) {
			user.setId(nextUserId++);
			userTable.put(user.getId(), user);
		}
	}

	public Optional<User> getUserByNameAndSurname (User user) {
		return userTable.values().stream()
				.filter(u -> u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword()))
				.findFirst();
	}

	public List<User> getAllUsers () {
		if (userTable.size() == 0)
			return new ArrayList<>();
		else {
			List<User> list = new ArrayList<>(userTable.values());
			return list;

		}
	}

	public User getUserById (Long id) {
		return userTable.get(id);
	}

	public boolean userTableContainsUser (Long id) {
		return userTable.containsKey(id);
	}

	public boolean deleteUserById (Long id) {
		User user = userTable.get(id);
		return userTable.remove(id, user);
	}


	@Override
	public void addFlight (Flight flight) {
		if (!flightTable.containsValue(flight)) {
			flight.setId(nextFlightId++);
			flightTable.put(flight.getId(), flight);
		}
	}

	@Override
	public void removeFlightById (Long id) {
		flightTable.remove(id);
	}

	@Override
	public Flight getFlightById (Long id) {
		return flightTable.get(id);
	}

	@Override
	public List<Flight> getAllFlights () {
		return new ArrayList<>(flightTable.values());
	}

	@Override
	public List<Flight> getAllUserFlights (User user) {
		return flightTable.values().stream()
				.filter(flight -> flight.getUser().equals(user))
				.collect(Collectors.toList());
	}

	@Override
	public boolean flightTableContainsId (Long id) {
		return flightTable.containsKey(id);
	}

	@Override
	public Ticket getTicketByFlightId (Long id) {
		return flightTable.get(id).getTicket();
	}

	@Override
	public User getUserByFlightId (Long id) {
		return flightTable.get(id).getUser();
	}

	@Override
	public boolean isUsersFlight (Long id, User user) {
		if (flightTable.containsKey(id))
			return flightTable.get(id).getUser().equals(user);
		return false;
	}

	@Override
	public boolean isFlightTableContainsTicket (Ticket ticket) {
		return flightTable.values().stream()
				.anyMatch(flight -> flight.getTicket().equals(ticket));
	}
}
