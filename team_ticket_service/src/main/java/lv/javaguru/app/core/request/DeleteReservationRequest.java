package lv.javaguru.app.core.request;

import lv.javaguru.app.core.domain.Person;
import lv.javaguru.app.core.domain.Reservation;
import lv.javaguru.app.core.domain.Ticket;

public class DeleteReservationRequest {

    private final Long id;

    public DeleteReservationRequest(Long id) {
        this.id = id;
    }

    public Long getId () {
        return id;
    }
}
