package lv.javaguru.app.database;

import lv.javaguru.app.core.domain.Reservation;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.domain.Ticket;

import java.util.*;
import java.util.stream.Collectors;

public class ReservationDatabase implements Database {

	private final Map<Long, Reservation> reservations = new HashMap<>();

	private static Long nextId = 1L;


	@Override
	public void addReservation (Reservation reservation) {
		if (!reservations.containsValue(reservation)) {
			reservation.setId(nextId++);
			reservations.put(reservation.getId(), reservation);
		}
	}

	@Override
	public void removeReservation (Reservation reservation) {
		if (reservations.containsValue(reservation))
			reservations.values().removeIf(res -> res.equals(reservation));
	}

	@Override
	public void removeReservationById (Long id) {
		reservations.remove(id);
	}

	@Override
	public Reservation getReservationById (Long id) {
		return null;
	}

	@Override
	public List<Reservation> getAllReservations () {
		return new ArrayList<>(reservations.values());
	}

	@Override
	public List<Reservation> getAllUserReservations (User user) {
		return reservations.values().stream()
				.filter(reservation -> reservation.getUser().equals(user))
				.collect(Collectors.toList());
	}

	@Override
	public boolean containsKey (Long id) {
		return reservations.containsKey(id);
	}

	public Ticket getReservationTicket (Long id) {
		return reservations.get(id).getTicket();
	}

	public User getReservationUser (Long id) {
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
