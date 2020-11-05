package lv.javaguru.app;

import lv.javaguru.app.entity.Person;
import lv.javaguru.app.entity.Ticket;

import java.util.*;

public class Main {
    private static final Map<Person, Ticket> reservations = new HashMap<>();

    public static void main(String[] args) {
        Repository repository = new Repository(reservations);
        Person person = new Person("Sergejs", "Aleksejevs");


        GregorianCalendar d = new GregorianCalendar(2020, Calendar.NOVEMBER, 5);
        GregorianCalendar r = new GregorianCalendar(2020, Calendar.NOVEMBER, 1);


        Ticket ticket = new Ticket("Riga", "Barcelona", d, r, 2, 3);
        System.out.println(ticket);

        repository.addReservation(person, ticket);
        System.out.println(repository);
    }
}
