package lv.javaguru.app;

import lv.javaguru.app.console_ui.*;
import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.domain.Person;
import lv.javaguru.app.core.domain.PersonType;
import lv.javaguru.app.core.services.*;
import lv.javaguru.app.core.validators.EditTicketRequestValidator;
import lv.javaguru.app.core.validators.LoginRequestValidator;
import lv.javaguru.app.core.validators.RegisterRequestValidator;
import lv.javaguru.app.database.InMemoryDatabase;
import lv.javaguru.app.core.validators.AddReservationRequestValidator;

public class Main {
    private static final InMemoryDatabase database = new InMemoryDatabase();
    private static AddReservationRequestValidator validator = new AddReservationRequestValidator();
    private static AddReservationService addReservationService = new AddReservationService(database, validator);

    private static LoginRequestValidator loginRequestValidator = new LoginRequestValidator();
    private static LogInService loginService = new LogInService(database, loginRequestValidator);
    private static UIActions logInAction = new LogInAction(loginService);

    private static RegisterRequestValidator registerRequestValidator = new RegisterRequestValidator();
    private static RegisterService registerService = new RegisterService(database, registerRequestValidator);
    private static UIActions registerAction = new RegisterAction(registerService);


    private static DeleteReservationService deleteReservationService = new DeleteReservationService(database);
    private static ShowReservationsService showReservationsService = new ShowReservationsService(database);

    private static final EditTicketRequestValidator editTicketRequestValidator = new EditTicketRequestValidator();
    private static final EditTicketService editTicketService = new EditTicketService(database, editTicketRequestValidator);

    private static UIActions editTicketAction = new EditTicketAction(editTicketService);

    private static UIActions addReservationAction = new AddReservationAction(addReservationService);
    private static UIActions deleteReservationAction = new DeleteReservationAction(deleteReservationService);
    private static UIActions showReservationsAction = new ShowReservationsAction(showReservationsService);
    private static UIActions exitAction = new ExitAction();

    private static final LogOutService logOutService = new LogOutService(database);
    private static final UIActions logOutAction = new LogOutAction(logOutService);


    public static void main(String[] args) {
        Person admin = new Person("ADMIN", "ADMIN");
        admin.setPersonType(PersonType.ADMIN);

        Person user = new Person("s", "a");
        user.setPersonType(PersonType.CLIENT);

        database.addPerson(admin);
        database.addPerson(user);

        while (true) {
            printInitMenu();
            int menuNumber = BaseFunc.getMenuNumberFromUser();
            switch (menuNumber) {
                case 1 -> {
                    logInAction.execute();                }
                case 2 -> {
                    registerAction.execute();
                }
                case 0 -> exitAction.execute();
                default -> {
                    BaseFunc.printLineSeparator();
                }
            }
        }
    }

    private static void printInitMenu() {
        BaseFunc.printHeader("LOGIN");
        BaseFunc.printLineSeparator();
        System.out.println(
                "[1] Login\n" +
                        "[2] Register\n" +
                        "[0] Exit");
    }


    public static void userMode_mainMenu(Person currentUser) {
        while (true) {
            printUserMode_MainMenu(currentUser);
            int menuNumber = BaseFunc.getMenuNumberFromUser();
            switch (menuNumber) {
                case 1 -> addReservationAction.execute();
                case 2 -> showReservationsAction.execute();
                case 3 -> editTicketAction.execute();
                case 4 -> deleteReservationAction.execute();
                case 0 -> {
                    logOutAction.execute();
                    return;
                }
                default -> {
                    System.out.println("Wrong input");
                    BaseFunc.printLineSeparator();
                }
            }
        }
    }

    public static void adminMode_mainMenu (Person currentUser) {
        while (true) {
            printAdminMode_MainMenu(currentUser);
            int menuNumber = BaseFunc.getMenuNumberFromUser();
            switch (menuNumber) {
                case 1 -> addReservationAction.execute();
                case 2 -> deleteReservationAction.execute();
                case 3 -> showReservationsAction.execute();
                case 0 -> {
                    logOutAction.execute();
                    return;
                }
                default -> {
                    System.out.println("Wrong input");
                    BaseFunc.printLineSeparator();
                }
            }
        }
    }



    private static void printAdminMode_MainMenu(Person currentUser) {
        BaseFunc.printHeader("MENU", currentUser);
        BaseFunc.printLineSeparator();
        System.out.println(
                "[1] Add person\n" +
                        "[2] Select person\n" +
                        "[3] Show all reservations\n" +
                        "[0] Log out");
    }

    private static void printUserMode_MainMenu(Person currentUser) {
        BaseFunc.printHeader("MENU", currentUser);
        BaseFunc.printLineSeparator();
        System.out.println(
                "[1] Add ticket to list\n" +
                        "[2] Show all reservations\n" +
                        "[3] Edit ticket\n" +
                        "[4] Delete ticket\n" +
                        "[0] Log out");
    }










}

