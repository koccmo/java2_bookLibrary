package lv.javaguru.app.database;

import lv.javaguru.app.core.domain.Person;
import lv.javaguru.app.core.domain.PersonType;
import lv.javaguru.app.core.domain.Reservation;
import lv.javaguru.app.core.domain.Ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryDatabase implements Database {

	private final Map<Person, List<Ticket>> reservations = new HashMap<>();
	private static Long nextId = 1L;
	private Person currentPerson;
	private Person selectedPerson;

	public Person getSelectedPerson () {
		return selectedPerson;
	}

	public void setSelectedPerson (Person selectedPerson) {
		this.selectedPerson = selectedPerson;
	}

	public Person getCurrentPerson () {
		return currentPerson;
	}

	public void setCurrentPerson (Person currentPerson) {
		this.currentPerson = currentPerson;
	}

	@Override
	public boolean containsPerson (Person person) {
		return reservations.containsKey(person);
	}

	@Override
	public void addTicket (Person person, Ticket ticket) {
		if (reservations.containsKey(person)) {
			ticket.setId(nextId++);
			reservations.get(person).add(ticket);
		}
	}

	public Person getPerson (Person person) {
		return reservations.keySet().stream()
				.filter(person::equals)
				.findFirst()
				.orElse(null);
	}

	@Override
	public Person getPerson (String name, String secondName) {
		return reservations.keySet().stream()
				.filter(person -> person.getName().equals(name) && person.getSurname().equals(secondName))
				.findFirst()
				.orElse(null);
	}

	@Override
	public void addPerson (Person person) {
		if (!reservations.containsKey(person))
			reservations.put(person, new ArrayList<>());
	}



	@Override
	public void removeTicketById (Long id) {
		reservations.get(currentPerson).removeIf(ticket -> ticket.getId().equals(id));
	}


	@Override
	public List<Ticket> getAllTickets (Person user) {
		if (reservations.containsKey(user) && user.getPersonType() != PersonType.ADMIN)
			return reservations.get(user);
		else {
			return reservations.values().stream()
					.flatMap(List::stream)
					.collect(Collectors.toList());
		}
	}


	@Override
	public Ticket getTicketById (Person person, Long id) {
		return reservations.get(person).stream()
				.filter(ticket -> ticket.getId().equals(id))
				.findFirst()
				.orElse(null);
	}


	@Override
	public boolean isContainTicketWithId (long id) {
		return reservations.values().stream()
				.flatMap(List::stream)
				.anyMatch(ticket -> ticket.getId().equals(id));
	}
}
