package lv.javaguru.app.database;

import lv.javaguru.app.entity.Person;
import lv.javaguru.app.entity.Ticket;

import java.util.Map;

public interface Database {
    void add(Person person, Ticket ticket);

    void removeByPerson(Person person);

    void removeById(Long id);

    Map<Person, Ticket> getAll();
}
