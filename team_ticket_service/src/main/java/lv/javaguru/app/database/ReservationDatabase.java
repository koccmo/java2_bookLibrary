package lv.javaguru.app.database;

import lv.javaguru.app.core.domain.Flight;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.domain.Ticket;

import java.util.*;
import java.util.stream.Collectors;

public class ReservationDatabase implements Database {

	private final Map<Long, Flight> reservations = new HashMap<>();

	private static Long nextId = 1L;


	@Override
	public void addReservation (Flight flight) {
		if (!reservations.containsValue(flight)) {
			flight.setId(nextId++);
			reservations.put(flight.getId(), flight);
		}
	}

	@Override
	public void removeReservation (Flight flight) {
		if (reservations.containsValue(flight))
			reservations.values().removeIf(res -> res.equals(flight));
	}

	@Override
	public void removeReservationById (Long id) {
		reservations.remove(id);
	}

	@Override
	public Flight getFlightById (Long id) {
		return reservations.get(id);
	}

	@Override
	public List<Flight> getAllReservations () {
		return new ArrayList<>(reservations.values());
	}

	@Override
	public List<Flight> getAllUserReservations (User user) {
		return reservations.values().stream()
				.filter(reservation -> reservation.getUser().equals(user))
				.collect(Collectors.toList());
	}

	@Override
	public boolean containsKey (Long id) {
		return reservations.containsKey(id);
	}

	public Ticket getTicketByFlightId (Long id) {
		return reservations.get(id).getTicket();
	}

	public User getUserByFlightId (Long id) {
		return reservations.get(id).getUser();
	}

	public boolean isUsersReservation (Long id, User user) {
		if (reservations.containsKey(id))
			return reservations.get(id).getUser().equals(user);
		return false;
	}
/*
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
	}*/
}
