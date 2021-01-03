import ui.events.*;

import java.util.Scanner;

public class EventApplication {

    private static ApplicationContext eventsApplication = new ApplicationContext();

    private static void eventsWork() {
        while (true) {
            menuOnDisplay();

            int userChoice = getUserChoice();

            switch (userChoice) {
                case 0 -> {
                    ((StartUpEventUIAction) eventsApplication.getBean(StartUpEventUIAction.class)).execute();
                }
                case 1 -> {
                    ((AddEventUIAction) eventsApplication.getBean(AddEventUIAction.class)).execute();
                }
                case 2 -> {
                    ((RemoveEventUIAction) eventsApplication.getBean(RemoveEventUIAction.class)).execute();
                }
                case 3 -> {
                    ((SearchEventUIAction) eventsApplication.getBean(SearchEventUIAction.class)).execute();
                }
                case 4 -> {
                    ((DisplayEventUIAction) eventsApplication.getBean(DisplayEventUIAction.class)).execute();
                }
                case 5 -> {
                    ((ExitEventUIAction) eventsApplication.getBean(ExitEventUIAction.class)).execute();
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

    public static void main(String[] args) {
        eventsWork();
        return;
    }
}