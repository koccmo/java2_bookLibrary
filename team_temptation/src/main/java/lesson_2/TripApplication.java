package lesson_2;

import lesson_2.database.Database;
import lesson_2.database.InMemoryDatabase;
import lesson_2.services.AddTripService;
import lesson_2.services.DisplayTripListService;
import lesson_2.services.RemoveTripService;
import lesson_2.ui.AddTripUIAction;
import lesson_2.ui.DisplayTripUIAction;
import lesson_2.ui.ExitTripUIAction;
import lesson_2.ui.RemoveTripUIAction;

import java.util.Scanner;

public class TripApplication {

    public static void main(String[] args) {

        Database database = new InMemoryDatabase();

        AddTripService addTripService = new AddTripService(database);
        AddTripUIAction addTripUIAction = new AddTripUIAction(addTripService);

        RemoveTripService removeTripService = new RemoveTripService(database);
        RemoveTripUIAction removeTripUIAction = new RemoveTripUIAction(removeTripService);

        DisplayTripListService displayTripListService = new DisplayTripListService(database);
        DisplayTripUIAction displayTripUIAction = new DisplayTripUIAction(displayTripListService);

        ExitTripUIAction exitTripUIAction = new ExitTripUIAction();

        while (true) {
            menuOnDisplay();

            int userChoice = getUserChoice();

            switch (userChoice) {
                case 1 -> {
                    addTripUIAction.execute();

                }
                case 2 -> {
                    removeTripUIAction.execute();

                }
                case 3 -> {
                    displayTripUIAction.execute();

                }
                case 4 -> {
                    exitTripUIAction.execute();
                }
            }
            System.out.println("");
        }


    }

    private static int getUserChoice() {
        System.out.println("Enter menu item number to execute:");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    private static void menuOnDisplay() {
        System.out.println("Program menu:");
        System.out.println("1. Add new trip");
        System.out.println("2. Delete trip");
        System.out.println("3. Show all trips");
        System.out.println("4. Exit");

        System.out.println("");
    }
}