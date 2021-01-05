//package ui.customers;
//
//import ui.events.ExitEventUIAction;
//import ui.UIAction;
//
//import java.util.Scanner;
//
//public class WorkWithGuidesUIAction implements UIAction {
//
//
//    @Override
//    public void execute() {
//        // DatabaseGuides databaseGuides = new InMemoryGuides();
////
////    AddGuideRequestValidator validatorAdd = new AddGuideRequestValidator();
//        //   AddGuideService addGuideService = new AddGuideService(databaseGuides, validatorAdd);
//        //   AddGuideUIAction addGuideUIAction = new AddGuideUIAction(addGuideService);
////
////    RemoveGuideRequestValidator validatorRemove = new RemoveGuideRequestValidator();
//        // RemoveGuideService removeGuideService = new RemoveGuideService(databaseGuides, validatorRemove);
////    RemoveGuideUIAction removeGuideUIAction = new RemoveGuideUIAction(removeGuideService);
////
////    DisplayGuideListService displayGuideListService = new DisplayGuideListService(databaseGuides);
////   DisplayGuideUIAction displayGuideUIAction = new DisplayGuideUIAction(displayGuideListService);
////
//        ExitEventUIAction exitEventUIAction = new ExitEventUIAction();
//        while (true) {
//            menuOnDisplay();
//
//            int userChoice = getUserChoice();
//
//            switch (userChoice) {
//                case 1 -> {
//                    //                  addGuideUIAction.execute();
//
//                }
//                case 2 -> {
//                    //                removeGuideUIAction.execute();
//
//                }
//                case 3 -> {
//                    //              displayGuideUIAction.execute();
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
//    private static int getUserChoice() {
//        System.out.println("Enter menu item number to execute:");
//        Scanner scanner = new Scanner(System.in);
//        return Integer.parseInt(scanner.nextLine());
//    }
//
//    private static void menuOnDisplay() {
//        System.out.println("Program menu:");
//        System.out.println("1. Add new guide");
//        System.out.println("2. Delete guide");
//        System.out.println("3. Show all guides");
//        System.out.println("4. Exit");
//
//        System.out.println();
//    }
//}