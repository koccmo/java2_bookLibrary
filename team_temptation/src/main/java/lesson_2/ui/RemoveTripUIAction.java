package lesson_2.ui;

import lesson_2.services.RemoveTripService;

import java.util.Scanner;

public class RemoveTripUIAction implements UIAction {

    private final RemoveTripService removeTripService;

    public RemoveTripUIAction(RemoveTripService removeTripService) {
        this.removeTripService = removeTripService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter trip name: ");
        String tripName = scanner.nextLine();
        removeTripService.removeTrip(tripName);
    }
}
