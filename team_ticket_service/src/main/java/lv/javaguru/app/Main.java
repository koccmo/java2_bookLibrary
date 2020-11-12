package lv.javaguru.app;

import lv.javaguru.app.businesslogic.AddReservationService;
import lv.javaguru.app.businesslogic.DeleteReservationService;
import lv.javaguru.app.businesslogic.GetAllReservationsService;
import lv.javaguru.app.database.Database;
import lv.javaguru.app.database.InMemoryDatabase;
import lv.javaguru.app.ui.AddReservationAction;
import lv.javaguru.app.ui.DeleteReservationAction;
import lv.javaguru.app.ui.ExitAction;
import lv.javaguru.app.ui.ShowReservationsAction;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Database database = new InMemoryDatabase();

        AddReservationService addReservationService = new AddReservationService(database);
        AddReservationAction addReservationAction = new AddReservationAction(addReservationService);

        DeleteReservationService deleteReservationService = new DeleteReservationService(database);
        DeleteReservationAction deleteReservationAction = new DeleteReservationAction(deleteReservationService);

        GetAllReservationsService getAllReservationsService = new GetAllReservationsService(database);
        ShowReservationsAction showReservationsAction = new ShowReservationsAction(getAllReservationsService);

        ExitAction exitAction = new ExitAction();

        while (true) {
            printProgramMenu();
            int menuNumber = getMenuNumberFromUser();
            switch (menuNumber) {
                case 1: {
                    addReservationAction.execute();
                    break;
                }
                case 2: {
                    System.out.println("Delete by: \n1. Person\n2. Id");
                    int submenuNumber = getMenuNumberFromUser();
                    switch (submenuNumber) {
                        case 1: {
                            deleteReservationAction.execute();
                            break;
                        }
                        case 2: {
                            System.out.println("Enter Id:");
                            Scanner scanner = new Scanner(System.in);
                            Long id =  Long.parseLong(scanner.nextLine());
                            deleteReservationAction.execute(id);
                            break;
                        }
                    }
                }
                case 3: {
                    showReservationsAction.execute();
                    break;
                }
                case 4: {
                    exitAction.execute();
                    break;
                }
            }
        }

    }


    private static void printProgramMenu() {
        System.out.println("\nProgram menu:\n" +
                "1. Add reservation to list\n" +
                "2. Delete reservation from list\n" +
                "3. Show all reservations in the list\n" +
                "4. Exit");
    }


    private static int getMenuNumberFromUser() {
        System.out.println("Enter menu item number to execute:");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }


}

