import database.Database;
import database.InMemoryDatabase;
import core.services.AddEventRequestValidator;
import core.services.AddEventService;
import core.services.DisplayEventListService;
import core.services.RemoveEventService;
import ui.AddEventUIAction;
import ui.DisplayEventUIAction;
import ui.ExitEventUIAction;
import ui.RemoveEventUIAction;

import java.util.Scanner;

public class EventApplication {

    public static void main(String[] args) {

        Database database = new InMemoryDatabase();

        AddEventRequestValidator validator = new AddEventRequestValidator();
        AddEventService addEventService = new AddEventService(database, validator);
        AddEventUIAction addEventUIAction = new AddEventUIAction(addEventService);

        RemoveEventService removeEventService = new RemoveEventService(database);
        RemoveEventUIAction removeEventUIAction = new RemoveEventUIAction(removeEventService);

        DisplayEventListService displayEventListService = new DisplayEventListService(database);
        DisplayEventUIAction displayEventUIAction = new DisplayEventUIAction(displayEventListService);

        ExitEventUIAction exitEventUIAction = new ExitEventUIAction();

        while (true) {
            menuOnDisplay();

            int userChoice = getUserChoice();

            switch (userChoice) {
                case 1 -> {
                    addEventUIAction.execute();

                }
                case 2 -> {
                    removeEventUIAction.execute();

                }
                case 3 -> {
                    displayEventUIAction.execute();

                }
                case 4 -> {
                    exitEventUIAction.execute();
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
        System.out.println("1. Add new event");
        System.out.println("2. Delete an event");
        System.out.println("3. Show all events");
        System.out.println("4. Exit");

        System.out.println("");
    }
}