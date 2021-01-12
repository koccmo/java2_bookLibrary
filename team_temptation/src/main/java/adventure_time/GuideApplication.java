//package adventure_time;
//
//import adventure_time.ui.guides.*;
//
//import java.util.Scanner;
//
//public class GuideApplication {
//
//    private static final ApplicationContextGuide guidesApplication = new ApplicationContextGuide();
//
//    private static void guidesWork() {
//        while (true) {
//            menuOnDisplay();
//
//            int userChoice = getUserChoice();
//
//            switch (userChoice) {
//                case 0 -> {
//                    ((StartUpGuideUIAction) guidesApplication.getBean(StartUpGuideUIAction.class)).execute();
//                }
//                case 1 -> {
//                    ((AddGuideUIAction) guidesApplication.getBean(AddGuideUIAction.class)).execute();
//                }
//                case 2 -> {
//                    ((RemoveGuideUIAction) guidesApplication.getBean(RemoveGuideUIAction.class)).execute();
//                }
//                case 3 -> {
//                    ((DisplayGuideUIAction) guidesApplication.getBean(DisplayGuideUIAction.class)).execute();
//                }
//                case 4 -> {
//                    ((ExitGuideUIAction) guidesApplication.getBean(ExitGuideUIAction.class)).execute();
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
//        System.out.println("0. Start with the defined DB");
//        System.out.println("1. Add new guide");
//        System.out.println("2. Delete guide");
//        System.out.println("3. Show all guides");
//        System.out.println("4. Exit");
//
//        System.out.println();
//    }
//    public static void main(String[] args) {
//        guidesWork();
//        return;
//    }
//}