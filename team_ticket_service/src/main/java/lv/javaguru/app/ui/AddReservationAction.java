package lv.javaguru.app.ui;

import lv.javaguru.app.businesslogic.AddReservationService;
import lv.javaguru.app.entity.Person;
import lv.javaguru.app.entity.Ticket;

import java.util.Scanner;

public class AddReservationAction implements UIActions {

    private final AddReservationService addReservationService;

    public AddReservationAction(AddReservationService addReservationService) {
        this.addReservationService = addReservationService;
    }

    @Override
    public void execute() {
        Ticket t = fillTicket();
        Person p = fillPerson();
        addReservationService.addReservation(p, t);
        System.out.println("Your reservation was added to list.");
    }

    private static Ticket fillTicket() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter departure: ");
        String departure = scanner.nextLine();

        System.out.println("Enter destination: ");
        String destination = scanner.nextLine();

        System.out.println("Enter departureDate: ");
        String departureDate = scanner.nextLine();

        System.out.println("Enter returnDate: ");
        String returnDate = scanner.nextLine();

        System.out.println("Enter seat: ");
        int row = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter seat: ");
        String seat = scanner.nextLine();

        return new Ticket(departure, destination, departureDate, returnDate,  seat);
    }

    private static Person fillPerson() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter name: ");
        String name = scanner.nextLine();

        System.out.println("Enter surname: ");
        String surname = scanner.nextLine();

        return new Person(name, surname);
    }
}
