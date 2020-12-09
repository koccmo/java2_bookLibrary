//package ui.customers;
//
//import core.services.events.*;
//import database.events.EventDatabase;
//import database.events.InMemoryEvents;
//import ui.events.AddEventUIAction;
//import ui.events.DisplayEventUIAction;
//import ui.events.ExitEventUIAction;
//import ui.events.RemoveEventUIAction;
//import ui.UIAction;
//
//import java.util.Scanner;
//
//public class WorkWithEventsUIAction implements UIAction {
//
//    @Override
//    public void execute() {
//        EventDatabase databaseEvents = new InMemoryEvents();
//
//        AddEventRequestValidator validatorAdd = new AddEventRequestValidator();
//        AddEventService addEventService = new AddEventService(databaseEvents, validatorAdd);
//        AddEventUIAction addEventUIAction = new AddEventUIAction(addEventService);
//
//        RemoveEventRequestValidator validatorRemove = new RemoveEventRequestValidator();
//        RemoveEventService removeEventService = new RemoveEventService(databaseEvents, validatorRemove);
//        RemoveEventUIAction removeEventUIAction = new RemoveEventUIAction(removeEventService);
//
//        DisplayEventListService displayEventListService = new DisplayEventListService(databaseEvents);
//        DisplayEventUIAction displayEventUIAction = new DisplayEventUIAction(displayEventListService);
//
//        ExitEventUIAction exitEventUIAction = new ExitEventUIAction();
//        while (true) {
//            menuOnDisplay();
//
//            int userChoice = getUserChoice();
//
//            switch (userChoice) {
//                case 1 -> {
//                    addEventUIAction.execute();
//
//                }
//                case 2 -> {
//                    removeEventUIAction.execute();
//
//                }
//                case 3 -> {
//                    displayEventUIAction.execute();
//
//                }
//                case 4 -> {
//                    exitEventUIAction.execute();
//                }
//            }
//            System.out.println();
//        }
//    }
//
//
//    private static int getUserChoice() {
//        System.out.println("Enter menu item number to execute:");
//        Scanner scanner = new Scanner(System.in);
//        return Integer.parseInt(scanner.nextLine());
//    }
//
//    private static void menuOnDisplay() {
//        System.out.println("Program menu:");
//        System.out.println("1. Add new event");
//        System.out.println("2. Delete an event");
//        System.out.println("3. Show all events");
//        System.out.println("4. Exit");
//
//        System.out.println();
//    }
//}