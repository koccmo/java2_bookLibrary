package lv.javaguru.app.database;

import lv.javaguru.app.core.domain.Person;
import lv.javaguru.app.core.domain.Reservation;
import lv.javaguru.app.core.domain.Ticket;

import java.util.List;
import java.util.Map;

public interface Database {
    // void register(Person person);
    Person getCurrentPerson();

    //void addReservation(Person person , Reservation reservation);
    Person getPerson(Person person);
    Person getPerson(String name, String secondName);

    void setCurrentPerson(Person currentPerson);

    boolean containsPerson(Person person);

    void addPerson(Person person);

    void addTicket(Person person, Ticket ticket);

    //void removeByPerson(Person person);

    void removeTicketById(Long id);

    List<Ticket> getAllTickets(Person user);

    boolean isContainTicketWithId(long id);

    Ticket getTicketById(Person person, Long id);

    List<Ticket> getTicketListByPersonId(Long id);
}
