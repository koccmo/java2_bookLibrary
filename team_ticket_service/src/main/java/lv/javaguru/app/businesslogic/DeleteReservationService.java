package lv.javaguru.app.businesslogic;

import lv.javaguru.app.database.Database;
import lv.javaguru.app.entity.Person;

public class DeleteReservationService {

    private final Database database;

    public DeleteReservationService(Database database) {
        this.database = database;
    }

    public void deleteReservation(Person person) {
        database.removeByPerson(person);
    }

    public void deleteReservation(Long id) {
        database.removeById(id);
    }

}
