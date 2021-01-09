package lesson_4.ui;

import lesson_4.core.requests.DeleteBookByIdRequest;
import lesson_4.core.responses.DeleteBookByIdResponse;
import lesson_4.core.services.DeleteBookByIdService;

import java.util.Scanner;

public class DeleteBookByIdUICommand implements UICommand {

    private final DeleteBookByIdService deleteBookByIdService;

    public DeleteBookByIdUICommand(DeleteBookByIdService deleteBookByIdService) {
        this.deleteBookByIdService = deleteBookByIdService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter book ID : ");
        Long id = scanner.nextLong();
        DeleteBookByIdRequest request = new DeleteBookByIdRequest(id);
        DeleteBookByIdResponse response = deleteBookByIdService.deleteBookByIdResponse(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(System.out::println);
        } else {
            if (response.isBookRemoved()) {
                System.out.println("\nBook with ID=" + id + " has been successfully removed from electronic library!");
            } else {
                System.out.println("\nSorry, book with ID=" + id + " has not been removed from electronic library.");
            }
        }
    }
}
