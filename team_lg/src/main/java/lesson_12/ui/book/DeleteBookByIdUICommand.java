package lesson_12.ui.book;

import lesson_12.core.requests.book.DeleteBookByIdRequest;
import lesson_12.core.responses.book.DeleteBookByIdResponse;
import lesson_12.core.services.book.DeleteBookByIdService;
import lesson_12.ui.UICommand;
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
