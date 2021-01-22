package lesson_10.ui;

import lesson_10.core.requests.DeleteBookByIdRequest;
import lesson_10.core.responses.DeleteBookByIdResponse;
import lesson_10.core.services.DeleteBookByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DeleteBookByIdUICommand implements UICommand {

    @Autowired
    private DeleteBookByIdService deleteBookByIdService;

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
