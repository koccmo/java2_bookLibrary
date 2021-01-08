package lv.javaguru.app.core.request;

import lv.javaguru.app.core.domain.Person;
import lv.javaguru.app.core.domain.Reservation;
import lv.javaguru.app.core.domain.Ticket;

public class EditReservationRequest {

    private final Reservation reservation;


    public EditReservationRequest(Reservation reservation) {
        this.reservation = reservation;
    }

    public Person getPerson() {
        return reservation.getPerson();
    }

    public Ticket getTicket() {
        return reservation.getTicket();
    }
}
