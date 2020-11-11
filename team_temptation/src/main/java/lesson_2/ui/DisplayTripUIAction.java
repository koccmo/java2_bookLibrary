package lesson_2.ui;

import lesson_2.Trip;
import lesson_2.services.DisplayTripListService;

public class DisplayTripUIAction implements UIAction {

    private final DisplayTripListService displayTripListService;

    public DisplayTripUIAction (DisplayTripListService displayTripListService) {
        this.displayTripListService = displayTripListService;
    }

    @Override
    public void execute() {
        System.out.println("Here is a list of trips: ");
        for (Trip item : displayTripListService.getTripList()) {
            System.out.println(item);
        }
        System.out.println("This is the end.");
    }
}
