package lv.javaguru.app;

import lv.javaguru.app.entity.Person;
import lv.javaguru.app.entity.Ticket;

import java.util.List;
import java.util.Map;

public class Repository {
    private final Map<Person, Ticket> reservations;

    public Repository(Map<Person, Ticket> reservations) {
        this.reservations = reservations;
    }

    public void addReservation(Person person, Ticket ticket){
        reservations.put(person, ticket);
    }

    public void removeReservation(Person person){
        reservations.remove(person);
    }

    @Override
    public String toString() {
        return "Repository{" +
                "reservations=" + reservations +
                '}';
    }
}
