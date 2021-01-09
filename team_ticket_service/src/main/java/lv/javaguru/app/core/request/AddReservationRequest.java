package lv.javaguru.app.core.request;

import lv.javaguru.app.core.domain.Person;
import lv.javaguru.app.core.domain.Reservation;
import lv.javaguru.app.core.domain.Ticket;

public class AddReservationRequest {


    private final Reservation reservation;

    public AddReservationRequest(Reservation reservation) {
        this.reservation = reservation;
    }

    public Person getPerson() {
        return reservation.getPerson();
    }

    public Ticket getTicket() {
        return reservation.getTicket();
    }
}
