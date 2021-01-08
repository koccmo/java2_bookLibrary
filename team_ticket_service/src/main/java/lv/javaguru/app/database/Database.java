package lv.javaguru.app.database;

import lv.javaguru.app.core.domain.Person;
import lv.javaguru.app.core.domain.Ticket;

import java.util.Map;

public interface Database {
    // void register(Person person);
    Person getCurrentPerson();

    void setCurrentPerson(Person currentPerson);

    boolean containsPerson(Person person);

    void add(Person person, Ticket ticket);

    void removeByPerson(Person person);

    void removeById(Long id);

    Map<Person, Ticket> getAll();
}
