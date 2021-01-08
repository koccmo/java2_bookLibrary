package lv.javaguru.app.console_ui;

import lv.javaguru.app.core.domain.Reservation;
import lv.javaguru.app.core.services.AddReservationService;
import lv.javaguru.app.core.domain.Person;
import lv.javaguru.app.core.domain.Ticket;
import lv.javaguru.app.core.request.AddReservationRequest;
import lv.javaguru.app.core.response.AddReservationResponse;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddReservationAction implements UIActions {

    private final AddReservationService addReservationService;

    public AddReservationAction(AddReservationService addReservationService) {
        this.addReservationService = addReservationService;
    }


    @Override
    public void execute() {
        Ticket ticket = fillTicket();
        Person person = fillPerson();
        Reservation reservation = new Reservation(person, ticket);

        AddReservationRequest request = new AddReservationRequest(reservation);
        AddReservationResponse response = addReservationService.execute(request);

        if (response.hasErrors())
            response.getErrorList().forEach(System.out::println);
        else
            System.out.println("Your reservation was added to list.");
    }

    private static boolean validateCity(String city) {
        Pattern pattern = Pattern.compile("[a-z]+", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(city);

        // return matcher.matches();
        return city.matches("(?i)[a-z]+");
    }

    private static String capitalize(String city) {
        city = city.toLowerCase().trim();
        city = city.substring(0, 1).toUpperCase() + city.substring(1);

        return city;
    }

    private static Ticket fillTicket() {
        Scanner scanner = new Scanner(System.in);
        int code = 0;
        String departure = "";

        while (code == 0) {
            System.out.println("Enter departure city: ");
            departure = scanner.nextLine();

            if (validateCity(departure)) {
                departure = capitalize(departure);
                code = 1;
            }

        }


        System.out.println("Enter destination city: ");
        String destination = scanner.nextLine();
        destination = destination.trim();

        System.out.println("Enter departureDate: ");
        String departureDate = scanner.nextLine();
        departureDate = departureDate.trim();

        System.out.println("Enter returnDate: ");
        String returnDate = scanner.nextLine();
        returnDate = returnDate.trim();

        System.out.println("Enter seat: ");
        String seat = scanner.nextLine();
        seat = seat.trim();

        return new Ticket(departure, destination, departureDate, returnDate, seat);
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
