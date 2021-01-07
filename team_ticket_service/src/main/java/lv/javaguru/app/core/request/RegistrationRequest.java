package lv.javaguru.app.core.request;

import lv.javaguru.app.core.domain.Person;

public class RegistrationRequest {
    private final Person person;

    public RegistrationRequest(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }
}
