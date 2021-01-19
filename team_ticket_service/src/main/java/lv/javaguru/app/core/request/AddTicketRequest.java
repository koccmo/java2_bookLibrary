package lv.javaguru.app.core.request;

import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.domain.Ticket;

public class AddTicketRequest {
    private final User currUser;
    private final Ticket ticket;

    public AddTicketRequest(User currUser, Ticket ticket) {
        this.currUser = currUser;
        this.ticket = ticket;
    }

    public User getCurrUser () {
        return currUser;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
