package lv.javaguru.app;

import lv.javaguru.app.console_ui.*;
import lv.javaguru.app.core.services.*;
import lv.javaguru.app.core.validators.LoginRequestValidator;
import lv.javaguru.app.core.validators.RegisterRequestValidator;
import lv.javaguru.app.database.Database;
import lv.javaguru.app.database.InMemoryDatabase;
import lv.javaguru.app.core.validators.AddReservationRequestValidator;


import java.util.*;

public class Main {
    private static final Database database = new InMemoryDatabase();
    private static AddReservationRequestValidator validator = new AddReservationRequestValidator();
    private static AddReservationService addReservationService = new AddReservationService(database, validator);

    private static LoginRequestValidator loginRequestValidator = new LoginRequestValidator();
    private static LoginService loginService = new LoginService(database, loginRequestValidator);
    private static UIActions loginAction = new LoginAction(loginService);

    private static RegisterRequestValidator registerRequestValidator = new RegisterRequestValidator();
    private static RegisterService registerService = new RegisterService(database, registerRequestValidator);
    private static UIActions registerAction = new RegisterAction(registerService);


    private static DeleteReservationService deleteReservationService = new DeleteReservationService(database);
    private static ShowReservationsService showReservationsService = new ShowReservationsService(database);


    private static UIActions addReservationAction = new AddReservationAction(addReservationService);
    private static UIActions deleteReservationAction = new DeleteReservationAction(deleteReservationService);
    private static UIActions showReservationsAction = new ShowReservationsAction(showReservationsService);
    private static UIActions exitAction = new ExitAction();

    public static void main(String[] args) {


        while (true) {
            printProgramMenu();
            int menuNumber = getMenuNumberFromUser();
            switch (menuNumber) {
                case 1 -> {
                    loginAction.execute();
                    getSubMenu();

                }
                case 2 -> {
                    registerAction.execute();
                }
                case 0 -> exitAction.execute();
                default -> System.out.println("Wrong input" + lineSeparator());
            }
        }


        /*  */
    }

    private static void printProgramMenu() {
        System.out.println("MENU:" + lineSeparator() + "\n" +
                "[1] Login\n" +
                "[2] Register\n" +
                "[0] Exit");
    }

    private static void getSubMenu() {
        while (true) {
            printProgramMenu2();
            int menuNumber = getMenuNumberFromUser();
            switch (menuNumber) {
                case 1 -> addReservationAction.execute();
                case 2 -> deleteReservationAction.execute();
                case 3 -> showReservationsAction.execute();
                case 0 -> exitAction.execute();
                default -> System.out.println("Wrong input" + lineSeparator());
            }
        }
    }

    private static void printProgramMenu2() {
        printHeader("MENU");
        System.out.println(lineSeparator() + "\n" +
                "[1] Add reservation to list\n" +
                "[2] Delete reservation from list\n" +
                "[3] Show all reservations in the list\n" +
                "[0] Exit");
    }

    private static void printHeader(String header) {
        String userName = database.getCurrentPerson().toString();
        int spaceCount = 50 - header.length() - userName.length();

        System.out.print(header + multipleChar(spaceCount, ' ') + userName);
    }

    public static int getMenuNumberFromUser() {
        System.out.println("Enter menu number:");
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        input = input.replaceAll("[^\\d]", "");
        if (!input.trim().isEmpty())
            try {
                return Integer.parseInt(input);
            } catch (Exception exception) {
                System.out.println("Wrong input!");
            }
        return -1;
    }

    private static String lineSeparator() {
        String newLine = "\n";
        return newLine.concat(new String(new char[50]).replaceAll("", "="));
    }

    private static String multipleChar(int count, char ch) {
        return "".concat(new String(new char[count]).replaceAll("", String.valueOf(ch)));
    }

}

