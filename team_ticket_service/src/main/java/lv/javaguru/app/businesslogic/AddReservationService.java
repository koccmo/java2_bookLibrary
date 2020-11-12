package lv.javaguru.app.businesslogic;

import lv.javaguru.app.database.Database;
import lv.javaguru.app.entity.Person;
import lv.javaguru.app.entity.Ticket;

public class AddReservationService {

    private final Database database;

    public AddReservationService(Database database) {
        this.database = database;
    }


    public void addReservation(Person person, Ticket ticket) {
        database.add(person, ticket);
    }
}
