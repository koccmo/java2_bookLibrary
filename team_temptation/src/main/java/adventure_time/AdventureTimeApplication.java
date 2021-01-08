//import ui.customers.WorkWithCustomersUIAction;
//import ui.customers.WorkWithEventsUIAction;
//import ui.customers.WorkWithGuidesUIAction;
//import ui.events.ExitEventUIAction;
//
//import java.util.Scanner;
//
//public class Application {
//
//    public static void main(String[] args) {
//
//        WorkWithGuidesUIAction workWithEventsUIAction = new WorkWithGuidesUIAction();
//        WorkWithCustomersUIAction workWithCustomersUIAction = new WorkWithCustomersUIAction();
//        WorkWithEventsUIAction workWithGuidesUIAction = new WorkWithEventsUIAction();
//
//        ExitEventUIAction exitEventUIAction = new ExitEventUIAction();
//
//        try {
//            while (true) {
//                menuOnDisplay();
//
//                int userChoice = getUserChoice();
//
//                switch (userChoice) {
//                    case 1 -> {
//                        workWithEventsUIAction.execute();
//                    }
//                    case 2 -> {
//                        workWithCustomersUIAction.execute();
//                    }
//                    case 3 -> {
//                        workWithGuidesUIAction.execute();
//                    }
//                    case 4 -> {
//                        exitEventUIAction.execute();
//                    }
//                }
//                System.out.println();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
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
//        System.out.println("1. Work with Events");
//        System.out.println("2. Work with Customers");
//        System.out.println("3. Work with Guides");
//        System.out.println("4. Exit");
//
//        System.out.println();
//    }
//}