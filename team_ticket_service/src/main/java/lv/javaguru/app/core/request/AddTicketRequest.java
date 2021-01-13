package lv.javaguru.app.core.request;

import lv.javaguru.app.core.domain.Person;
import lv.javaguru.app.core.domain.Ticket;

public class AddTicketRequest {
    private final Person currUser;
    private final Ticket ticket;

    public AddTicketRequest(Person currUser, Ticket ticket) {
        this.currUser = currUser;
        this.ticket = ticket;
    }

    public Person getCurrUser () {
        return currUser;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
