package lv.javaguru.app.core.request;

import lv.javaguru.app.core.domain.Person;
import lv.javaguru.app.core.domain.Ticket;

public class AddReservationRequest {

    private final Person person;
    private final Ticket ticket;

    public AddReservationRequest(Person person, Ticket ticket) {
        this.person = person;
        this.ticket = ticket;
    }

    public Person getPerson() {
        return person;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
