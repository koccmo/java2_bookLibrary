package lv.javaguru.app.core.request;

import lv.javaguru.app.core.domain.Person;
import lv.javaguru.app.core.domain.Reservation;
import lv.javaguru.app.core.domain.Ticket;

public class LoginRequest {
    private final Person person;

    public LoginRequest(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

}
