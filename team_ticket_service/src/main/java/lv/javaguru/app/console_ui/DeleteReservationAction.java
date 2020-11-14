package lv.javaguru.app.console_ui;

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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name: ");
        String name = scanner.nextLine();

        System.out.println("Enter surname: ");
        String surname = scanner.nextLine();

        deleteReservationService.deleteReservation(new Person(name, surname));
    }

    public void execute(Long id) {
        deleteReservationService.deleteReservation(id);
    }
}
