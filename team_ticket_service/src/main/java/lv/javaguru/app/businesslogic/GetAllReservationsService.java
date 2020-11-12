package lv.javaguru.app.businesslogic;

import lv.javaguru.app.database.Database;
import lv.javaguru.app.entity.Person;
import lv.javaguru.app.entity.Ticket;

import java.util.Map;

public class GetAllReservationsService {

    private final Database database;

    public GetAllReservationsService(Database database) {
        this.database = database;
    }

    public void getAll() {
        System.out.println("Reservation list: ");

        database.getAll().entrySet().forEach(System.out::println);
    }
}
