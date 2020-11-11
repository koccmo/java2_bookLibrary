package lesson_2.ui;

import lesson_2.services.AddTripService;

import java.util.Scanner;

public class AddTripUIAction implements UIAction {

    private final AddTripService addTripService;

    public AddTripUIAction(AddTripService addTripService) {
        this.addTripService = addTripService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter trip name: ");
        String tripName = scanner.nextLine();
        System.out.println("Enter a date the trip is starting: ");
        String startDate = scanner.nextLine();
        System.out.println("Enter a date the trip finishes: ");
        String finishDate = scanner.nextLine();
        System.out.println("Enter a trip description: ");
        String detailDescription = scanner.nextLine();
        addTripService.addTrip(tripName, startDate, finishDate, detailDescription);
        System.out.println("Your trip was added to list.");
    }
}