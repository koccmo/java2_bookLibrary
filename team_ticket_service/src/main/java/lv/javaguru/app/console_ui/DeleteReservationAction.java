package lv.javaguru.app.console_ui;

import lv.javaguru.app.Main;
import lv.javaguru.app.core.services.DeleteReservationService;
import lv.javaguru.app.core.domain.Person;

import java.util.Scanner;

public class DeleteReservationAction implements UIActions {

    private final DeleteReservationService deleteReservationService;

    public DeleteReservationAction(DeleteReservationService deleteReservationService) {
        this.deleteReservationService = deleteReservationService;
    }

    @Override
    public void execute() {
        deleteReservationBy();
    }

    private void deleteReservationBy() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printSubmenu();
            int selected = Main.getMenuNumberFromUser();
            switch (selected) {
                case 1 -> {
                    System.out.println("Enter Id: ");
                    Long id = Long.parseLong(scanner.nextLine());
                    deleteReservationService.deleteReservation(id);
                }
                case 2 -> {
                    System.out.println("Enter name: ");
                    String name = scanner.nextLine();

                    System.out.println("Enter surname: ");
                    String surname = scanner.nextLine();

                    deleteReservationService.deleteReservation(new Person(name, surname));
                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("Wrong input" );
            }
        }
    }

    private void printSubmenu() {
        System.out.println("Delete by:\n" +
                "[1] Reservation Id\n" +
                "[2] Full name \n" +
                "[0] Back to menu");
    }
}
