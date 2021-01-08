package lv.javaguru.app.database;

import lv.javaguru.app.core.domain.Person;
import lv.javaguru.app.core.domain.Ticket;

import java.util.HashMap;
import java.util.Map;

public class InMemoryDatabase implements Database {

    private final Map<Person, Ticket> reservations = new HashMap<>();
    private Long nextId = 1L;
    private Person currentPerson;

    public Person getCurrentPerson() {
        return currentPerson;
    }

    public void setCurrentPerson(Person currentPerson) {
        this.currentPerson = currentPerson;
    }

    @Override
    public boolean containsPerson(Person person) {
        return reservations.containsKey(person);
    }

    @Override
    public void add(Person person, Ticket ticket) {
        //  ticket.setId(nextId);
        // nextId++;
        reservations.put(person, ticket);
    }

    @Override
    public void removeByPerson(Person person) {
        reservations.remove(person);
    }

    @Override
    public void removeById(Long id) {
        reservations.entrySet().stream()
                .filter(e -> e.getValue().getId().equals(id))
                .findFirst()
                .map(Map.Entry::getKey)
                .ifPresent(reservations::remove);
    }

    @Override
    public Map<Person, Ticket> getAll() {
        return reservations;
    }


}
