package lv.javaguru.app.database;

import lv.javaguru.app.core.domain.Flight;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.domain.Ticket;
import org.springframework.stereotype.Component;


import java.util.*;
import java.util.stream.Collectors;

@Component
public class FlightDatabase implements Database {

	private final Map<Long, Flight> flights = new HashMap<>();

	private static Long nextId = 1L;


	@Override
	public void addFlight (Flight flight) {
		if (!flights.containsValue(flight)) {
			flight.setId(nextId++);
			flights.put(flight.getId(), flight);
		}
	}

	@Override
	public void removeFlight (Flight flight) {
		if (flights.containsValue(flight))
			flights.values().removeIf(res -> res.equals(flight));
	}

	@Override
	public void removeFlightById (Long id) {
		flights.remove(id);
	}

	@Override
	public Flight getFlightById (Long id) {
		return flights.get(id);
	}

	@Override
	public List<Flight> getAllFlights () {
		return new ArrayList<>(flights.values());
	}

	@Override
	public List<Flight> getAllUserFlights (User user) {
		return flights.values().stream()
				.filter(flight -> flight.getUser().equals(user))
				.collect(Collectors.toList());
	}

	@Override
	public boolean containsKey (Long id) {
		return flights.containsKey(id);
	}

	public Ticket getTicketByFlightId (Long id) {
		return flights.get(id).getTicket();
	}

	public User getUserByFlightId (Long id) {
		return flights.get(id).getUser();
	}

	public boolean isUsersFlight (Long id, User user) {
		if (flights.containsKey(id))
			return flights.get(id).getUser().equals(user);
		return false;
	}

	@Override
	public boolean isContainTicket (Ticket ticket) {
		return flights.values().stream()
				.anyMatch(flight -> flight.getTicket().equals(ticket));
	}

}
