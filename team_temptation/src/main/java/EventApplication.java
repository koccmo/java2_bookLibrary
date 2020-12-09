import core.services.events.*;
import database.events.EventDatabase;
import database.events.InMemoryEvents;
import ui.events.*;

import java.util.Scanner;

public class EventApplication {

    public static void main(String[] args) {

        EventDatabase databaseEvents = new InMemoryEvents();

        StartUpEventUIAction startUp = new StartUpEventUIAction(databaseEvents);

        AddEventRequestValidator validatorAdd = new AddEventRequestValidator();
        AddEventService addEventService = new AddEventService(databaseEvents, validatorAdd);
        AddEventUIAction addEventUIAction = new AddEventUIAction(addEventService);

        RemoveEventRequestValidator validatorRemove = new RemoveEventRequestValidator();
        RemoveEventService removeEventService = new RemoveEventService(databaseEvents, validatorRemove);
        RemoveEventUIAction removeEventUIAction = new RemoveEventUIAction(removeEventService);

        SearchEventRequestValidator validatorSearch = new SearchEventRequestValidator();
        SearchEventService searchEventService = new SearchEventService(databaseEvents, validatorSearch);
        SearchEventUIAction searchEventUIAction = new SearchEventUIAction(searchEventService);

        DisplayEventListService displayEventListService = new DisplayEventListService(databaseEvents);
        DisplayEventUIAction displayEventUIAction = new DisplayEventUIAction(displayEventListService);

        ExitEventUIAction exitEventUIAction = new ExitEventUIAction();
        while (true) {
            menuOnDisplay();

            int userChoice = getUserChoice();

            switch (userChoice) {
                case 0 -> {
                    startUp.execute();
                }
                case 1 -> {
                    addEventUIAction.execute();
                }
                case 2 -> {
                    removeEventUIAction.execute();
                }
                case 3 -> {
                    searchEventUIAction.execute();
                }
                case 4 -> {
                    displayEventUIAction.execute();
                }
                case 5 -> {
                    exitEventUIAction.execute();
                }
            }
            System.out.println();
        }
    }


    private static int getUserChoice() {
        System.out.println("Enter menu item number to execute:");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    private static void menuOnDisplay() {
        System.out.println("Program menu:");
        System.out.println("0. Start with the defined DB");
        System.out.println("1. Add new event");
        System.out.println("2. Delete an event");
        System.out.println("3. Search events");
        System.out.println("4. Show all events");
        System.out.println("5. Exit");

        System.out.println();
    }
}